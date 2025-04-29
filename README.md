
# Notification Scheduling

This project was developed for technical challenge from Magalu demonstrating my Java Backend Developer habilities and its scope is to register notifications for later sending.


## API Reference

#### Register pending notifications

```http
  POST /api/v1/scheduling
```

| Body | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `recipientEmail` | `string` | **Required**. Recipient's email |
| `recipientPhone` | `string` | **Required**. Recipient's phone number |
| `message` | `string` | **Required**. Notification's message |
| `sendDateTime` | `LocalDateTime` | **Required**. Event date and time in format (dd-MM-yyyy HH:mm:ss) |

#### Get a notification by id

```http
  GET /api/v1/scheduling/${id}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `Long` | **Required**. Id of notification to get |

#### Cancel a notification by id

```http
  GET /api/v1/scheduling/${id}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `Long` | **Required**. Id of notification to cancel |


## Run Locally

Clone the project

```bash
  git clone https://github.com/gablsl/scheduling-notifications-api.git
```

Run docker-compose.yml

```bash
  docker compose up --build
```


## Running Tests

To run tests, run the following command

```bash
  mvn test
```

