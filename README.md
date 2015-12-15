# tp-storm

If you want to launch the practical on your own, follow the steps :

## Install Redis
Download last stable Redis on http://redis.io/download (3.0.5 at this time).
Install it as explained on the page.

## Launch Redis
In Redis folder, enter in your terminal:

    ./src/redis-server

## Launch the jar file in the import-data folder
In your terminal, go in the import-data folder then:

    java -jar import-data-1.0-SNAPSHOT.jar bzh.telecom.tp.storm.ImportDataLauncher

## Launch your own version of practical with Redis URL "localhost"
