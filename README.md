# tweeter
 
Hi,

The repository is in disarray. To get this up and running keep in mind:
1. It uses an H2 database with some commented out code to connect to an AWS RDS instance.
2. Most endpoints are borked due to the Java Version upgrade and migration processes I'm partially ignoring right now
3. I moved packages around which has faulted Spring's ability to auto-discover classes. I'll figure that out later


To get this running:
1. Import the project with as `maven`
2. `clean`, `compile` and `package` to ensure application context can load
3. Setup your run profile and hit it!
4. Use either the command prompt or browser to test the connection

```url
http://localhost:5000/v1/
```

or 

```bash
curl --verbose http://localhost:5000/v1/
```
