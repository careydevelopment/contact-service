# Ecosystem User Service

This is a Spring Boot application that's used with the "Building a CRM Application " series on the <a href="https://careydevelopment.us" target="_blank">Carey Development website</a>.

It's a microservice that handles requests related to users within an ecosystem.

Each branch within this repo is related to a distinct guide. The master branch holds the latest version of the application.

If you want to follow along with the series, just visit the URL that points to the <a href="https://careydevelopment.us/tag/careydevelopmentcrm" target="_blank">careydevelopmentcrm tag</a>. 

Remember, all guides are in reverse chronological order so if you want to start from the beginning, you'll need to go to the last page.

## You Need to Make Updates
Bad news: you can't just clone this source and run it right out of the box. You'll need to make some changes.

For example, you'll need to update the MongoDB connection string in application.properties.

You might like to make some other configuration changes as well (e.g., maximum upload file size).

## Configuration
Although some of the configuration options are located in application.properties, not all of them are there.

Some info, like the JWT secret and the MongoDB connection string, are located in a separate properties file stored outside of the source repo. That's
so I don't accidentally check in secure info.

The location of the external properties file is specified in the application.properties file. It's the ecosystem.properties.file.location property.

Here's a list of the properties in that external file:
jwt.secret - the secret used to sign JWTs
mongodb.carey-ecosystem.connection - the connection string to get to the MongoDB (in the format: mongodb://name:password@server:port)

## The UI
The Carey Development CRM <a href="https://github.com/careydevelopment/careydevelopmentcrm">source</a> uses this service.


