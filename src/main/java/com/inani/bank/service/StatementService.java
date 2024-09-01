package com.inani.bank.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.inani.bank.request.StatementRequest;
import com.oracle.bmc.auth.ConfigFileAuthenticationDetailsProvider;
import com.oracle.bmc.objectstorage.ObjectStorageClient;
import com.oracle.bmc.objectstorage.requests.PutObjectRequest;
import com.oracle.bmc.objectstorage.responses.PutObjectResponse;

@Service
public class StatementService {

  private static Logger LOGGER = LoggerFactory.getLogger(StatementService.class);

  // external properties stored in /config/application.properties
  @Value("${storage.bucket.namespace:NA}")
  private String namespaceName;

  @Value("${storage.bucket.name:NA}")
  private String bucketName;

  @Value("${local.statement.storage.path:./Statements}")
  private String statementPath;

  @Value("${object.storage.enabled:false}")
  private boolean isObjectStorageEnabled;

  public StatementService() {
  }

  public String getStatement(StatementRequest statementRequest) throws IOException {

    String fileName = "Statement_" + statementRequest.getAccountNumber() + ".csv";

    // Create CSV content
    String csvContent = "ID,Name,Email\n1,John Doe,john.doe@example.com\n2,Jane Doe,jane.doe@example.com";

    if (isObjectStorageEnabled) {

      // Save CSV to a temporary file
      Path tempFilePath = Files.createTempFile("oci-csv-", ".csv");
      Files.write(tempFilePath, csvContent.getBytes(StandardCharsets.UTF_8));

      // Upload the file to OCI Object Storage
      /*
       * InstancePrincipalsAuthenticationDetailsProvider provider =
       * InstancePrincipalsAuthenticationDetailsProvider.builder()
       * .build();
       */

      /*
       * bucket-config file is of format
       * [DEFAULT]
       * user=(ocid1.user.oc1.......)
       * fingerprint=()......)
       * tenancy=(ocid1.tenancy.oc1........)
       * region=(region-code)
       * key_file=(private user api key file path)
       */
      ConfigFileAuthenticationDetailsProvider provider = new ConfigFileAuthenticationDetailsProvider(
          "./config/bucket-config",
          "DEFAULT");
      ObjectStorageClient objectStorageClient = ObjectStorageClient.builder().build(provider);

      PutObjectRequest putObjectRequest = PutObjectRequest.builder()
          .namespaceName(namespaceName)
          .bucketName(bucketName)
          .objectName(fileName)
          .putObjectBody(new ByteArrayInputStream(Files.readAllBytes(tempFilePath)))
          .build();

      // LOGGER.info("Request created successfully");

      PutObjectResponse putObjectResponse = objectStorageClient.putObject(putObjectRequest);

      objectStorageClient.close();

      // LOGGER.info("put object request done");

      // Generate the file URL
      String fileUrl = String.format("https://objectstorage.%s.oraclecloud.com/n/%s/b/%s/o/%s",
          provider.getRegion().getRegionId(), namespaceName, bucketName, fileName);

      // Output the URL
      if (putObjectResponse.get__httpStatusCode__() == HttpStatus.OK.value()) {
        LOGGER.info("File uploaded successfully: " + fileUrl);
        return fileUrl;
      } else {
        LOGGER.info("Upload failed");
        return "Failed";
      }
    } else {
      // Ensure the directory exists
      Path directory = Paths.get(statementPath);
      if (!Files.exists(directory)) {
        Files.createDirectories(directory);
      }

      // Create the file in the specified directory
      Path filePath = directory.resolve(fileName);
      Files.write(filePath, csvContent.getBytes(StandardCharsets.UTF_8));

      // Return the path to the file
      return filePath.toString();
    }
  }
}