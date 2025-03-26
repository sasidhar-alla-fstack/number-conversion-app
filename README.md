# 1. UI React App - To run in local machine

## 1.1 For initial setup or download libraries
> cd {directory where application downloaded}/number-conversion-app/ui

> ### `npm install`

## 1.2 To run application
> ### `npm run build`
> ### `npm start`

Will automatically open in default browser or click http://localhost:3000.
If backend service is not up already will give an Network Error alert when user tries to submit

# 2. Backend WebService - To run in local machine

## 2.1 Prerequisites: 
### Gradle
>If gradle is not setup already. See Gradle installation guidelines [https://gradle.org/install/]

## 2.2 Change directory 
>cd {directory where application downloaded}/number-conversion-app/webservice
> 
## 2.2 Build
> ### `gradle build`

## 2.3 Run 
> ### `gradle bootRun`
> This should run application on http://localhost:8080.Try with endpoint http://localhost:8080/romannumeral?query=1

# 3. Run both the applications using docker in local machine
## 3.1 Prerequisites: 
>  Docker up and running in local machine. If it doesn't exist already see instructions [https://www.docker.com/get-started/]

## 3.2 use this cmd in terminal to run application
> docker-compose up -d --build

## 3.3 Verification 
> Open http://localhost:3000, now should be able to see UI application
> Type any number between 1-3999
> Click on button `Convert to roman number`, this should return successful response. 
> If any issues with backend server will give an alert as Network error.

# Metrics 
* Used prometheus an library `io.micrometer:micrometer-registry-prometheus` to add metrics 
* When application is successfully run on docker metrics will be able to view in prometheus port
* http://localhost:9090/graph?g0.expr=romannumeral_request_count_total&g0.tab=0&g0.display_mode=stacked&g0.show_exemplars=1&g0.range_input=1h
* to see other metrics see http://localhost:8080/actuator/prometheus (not visualized)

# Logs
* Logs can be viewed in docker logs
* Check for right docker container names with cmd - docker ps
* ui logs cmd: docker logs -f number-conversion-app-frontend-1 
* webservice logs cmd: docker logs -f number-conversion-app-backend-1


