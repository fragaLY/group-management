call gradle clean --parallel
call gradle build
call start "Chrome" "http:/localhost:8080/"
call gradle jettyStart