ToDo: 
- turn httpEntityBuilder into a factory
- add ingress config
- add routes for ladder-service
- hook it up to skaffold
- modify db connection properties to use config map variables
- create deployment.yaml for ladder service
- get ladder service and db talking to each other in the running eco-system

- design front end using angular

K8 Configuration: https://www.youtube.com/watch?v=vrjoSsiKZaA

*** Tips ***
- generate base 64 strings for k8 secrets
    - echo -n 'postgres' | base64
    

