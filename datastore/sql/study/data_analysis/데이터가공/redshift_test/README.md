
```
workbench/J Download : https://www.sql-workbench.eu/downloads.html 
redshift-jdbc-driver Download : https://s3.amazonaws.com/redshift-downloads/drivers/jdbc/1.2.12.1017/RedshiftJDBC41-1.2.12.1017.jar 
aws-redshift install & start & security group inbound tcp port 5439 open

## workbench/J
Driver Select & URL(jdbc:redshift://[cluster-name].~)
username & password 
autocommit check
Connect
```

```sql
CREATE TABLE purchase_log_per_day(
  dt                     varchar(255)
  , total_amount         integer
  , seven_day_avg        integer
  , seven_day_avg_strict integer
);
```

```sql
INSERT INTO purchase_log_per_day (dt, total_amount, seven_day_avg, seven_day_avg_strict)

SELECT  
dt
, SUM(purchase_amount) AS total_amount

, AVG(SUM(purchase_amount)) 
  OVER (ORDER BY dt ROWS BETWEEN 6 PRECEDING AND CURRENT ROW) 
  AS seven_day_avg
, CASE 
    WHEN 7 = COUNT(*) 
      OVER (ORDER BY dt ROWS BETWEEN 6 PRECEDING AND CURRENT ROW)
    THEN AVG(SUM(purchase_amount)) 
      OVER (ORDER BY dt ROWS BETWEEN 6 PRECEDING AND CURRENT ROW)
  END
  AS seven_day_avg_strict
FROM purchase_log
GROUP BY dt
ORDER BY dt;
```