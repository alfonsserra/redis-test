
# `redis-test` — Test Redis using Jedis API

This project is a test for [Redis][redis], an open source (BSD licensed), in-memory data structure store, used as a database, cache and message broker.
[JRedis API][jredis] is used as a client.

## Getting Started

To get you started you can simply clone the `redis-test` repository and install the dependencies:

### Prerequisites

You need [git][git] to clone the `redis-test` repository.

You will need [Java™ SE Development Kit 8][jdk-download] and [Maven][maven].

### Clone `redis-test`

Clone the `redis-test` repository using git:

```bash
git clone https://github.com/systelab/redis-test.git
cd seed-jee
```

If you just want to start a new project without the `redis-test` commit history then you can do:

```bash
git clone --depth=1 https://github.com/systelab/redis-test.git <your-project-name>
```

The `depth=1` tells git to only pull down one commit worth of historical data.

### Install Dependencies

In order to install the dependencies you must run:

```bash
mvn install
```

### Run

To start a Redis server, it is recommended to use the Docker image:

```bash
docker run --name my-redis –p 6379:6379 -d redis
```

To run the client, run the following bash command:

```bash
docker exec -it my-redis redis-cli
```

For the moment the repository only contains a test class.


[git]: https://git-scm.com/
[maven]: https://maven.apache.org/download.cgi
[jdk-download]: http://www.oracle.com/technetwork/java/javase/downloads
[redis]: https://redis.io/
[jredis]: https://github.com/alphazero/jredis
