1. Create a PostgreSQL database using the provided Docker Compose file:

   ```shell
   docker compose up -d
   ```
2. Run `mvn test`
3. There should be an error like the following:

    ```shell
    [ERROR] Errors:
    [ERROR]   WrapperTest.query_wrapper:77 » Execution A problem occurred in the SQL executor : No JDBC mapping could be
    inferred for parameter - org.hibernate.sql.exec.internal.SqlTypedMappingJdbcParameter@213ceb4e
    ```
