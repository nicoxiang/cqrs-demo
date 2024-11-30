docker network create --attachable -d bridge techbankNet

cd yaml/kafka
docker-compose up -d

mkdir -p /data/mongodb_data_container

docker run -it -d --name mongo-container -p 27017:27017 --network techbankNet --restart always -v /data/mongodb_data_container:/data/db mongo:8.0

docker run -it -d --name mysql-container -p 3306:3306 --network techbankNet -e MYSQL_ROOT_PASSWORD=123456 --restart always -v /data/mysql_data_container:/var/lib/mysql mysql:5.7