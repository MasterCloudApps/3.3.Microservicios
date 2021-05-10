eval $(minikube docker-env)

pushd services/products
mvn package -DskipTests=true
docker build -t codeurjc/products .
popd

pushd services/customers
mvn package -DskipTests=true
docker build -t codeurjc/customers .
popd

pushd services/orders
mvn package -DskipTests=true
docker build -t codeurjc/orders .
popd

pushd services/api-gateway
mvn package -DskipTests=true
docker build -t codeurjc/api-gateway .
popd

pushd services/notifications
mvn package -DskipTests=true
docker build -t codeurjc/notifications .
popd

kubectl delete -f k8s
kubectl apply -f k8s
