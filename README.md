# Recruitment Task 

### Clone Repository
- Clone this repo to your local machine using `https://github.com/Greedion/Recruitment-task.git`

### Install algorithm in local repository and build task package
- Go to `pom.xml` in `parrent` directory. If needed change variable`<path.to.bash>` to your bash (with access to maven environment variables) .
#### From console
- Run command  `mvn validate`.
#### From Intelij IDEA
- Go to `Edit Run/Debug configurations` dialog.
- Click `edit configurations`.
- Click `Add New Configuration`.
- Select `Maven`.
- In the bookmark `Parameters` in field `Working directory` set `Parrent`.
- Click `Apply`, and `ok`.
- Run the selected configuration.
#### Using Maven commands
-Go to `parrent` directory.
-Run command `mvn -f Task/algorithm/pom.xml clean install`
-Run command `mvn -f ./Task/pom.xml clean package`

### How to run in Docker
- Follow the above points.
- Go to directory with `Dockerfile`.
- Run command `docker build -f Dockerfile -t {your-app-name} . `
- Run command `docker images` and check your image id.
- Run command `docker run -p {your-port-outside}:8080 {your-image-id}`.
