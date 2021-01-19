call mvn clean install
call docker build . -t stock-quote-image
call docker run -t --link mysql8:mysql -p 8081:8081 --name stock-quote-container stock-quote-image

