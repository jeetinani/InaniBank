Hi,

This is as simple Banking Application Project built by passion and zeal to learn SpringBoot(backend) and ReactJS(frontend).

This project uses sqlite database to persist data.
Simple session token authentication is used to manage login sessions.
The ReactJS frontend is packed into a static build and copied into Spring project's static folder and served using a thymeleaf index.html template.


There is a github action created to Build the project into a jar and place it in a linux instance using scp.
Then action will then ssh into the linux instance and start a systemctl service (bank-application.service) in the instance to run the jar.
The preconfigured service - bank-application.service - is placed in /etc/systemd/system folder.
Once you place the file there, you need to enable it and reload systemctl.

```bash
  #enable
  sudo systemctl enable bank-application

  #reload
  sudo systemclt daemon-reload
```
