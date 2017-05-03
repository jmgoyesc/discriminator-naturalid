# discriminator-naturalid
Hibernate issue with discriminator and natural-id

I have a table to store parameters by type. For example, I will store a list of categories and a list of events. 
So, I am using a discriminator column, type.

```sql
create table parameter (    
id varchar(255) generated by default as identity,    
type varchar(255) not null,    
code varchar(255),    
name varchar(255),    
primary key (id)
);
```

 * When type is equals to EVENT-TYPE, it means that it is an event.
 * When type is equals to CATEGORY-TYPE, it means that it is a category.
 
I am using hbm.xml mapping.

I mapped two entities. Event entity for events and Category entity for categories.

The special case is with the event mapping because I am using a natural-id for the column code.

```xml
<hibernate-mapping xmlns="http://www.hibernate.org/xsd/orm/hbm" package="com.personal.hibernate.model">
    <class name="Event" table="Parameter" discriminator-value="EVENT-TYPE">
        <id name="id" type="java.lang.String">
            <column name="id"/>
            <generator class="identity"/>
        </id>
        <discriminator column="type" force="true"/>
        <natural-id mutable="true">
            <property name="code" column="code" type="java.lang.String"/>
        </natural-id>
        <property name="name" column="name" type="java.lang.String"/>
    </class>
</hibernate-mapping>
```

My case is searching events by natural id. 

I wrote a test case, **AppTest.test**. Before execute the test I am inserting categories and events. For example:

Categories:

id | type | code | name 
--- | --- | --- | ---
1 | CATEGORY-TYPE | 1 | Category 1
2 | CATEGORY-TYPE | 2 | Category 2

Events:

id | type | code | name 
--- | --- | --- | ---
3 | EVENT-TYPE | 1 | Event 1
4 | EVENT-TYPE | 2 | Event 2

And when I try to execute this:

```java
Event event = session.byNaturalId(Event.class)
				.using("code", "1")
				.load();
```

I got a null value because Hibernate is executing the following queries:

Get the id:

```sql
    select
        event_.id as id1_0_ 
    from
        parameter event_ 
    where
        event_.code=?
```

Get the object:

```sql
    select
        event0_.id as id1_0_0_,
        event0_.code as code3_0_0_,
        event0_.name as name4_0_0_ 
    from
        parameter event0_ 
    where
        event0_.id=? 
        and event0_.type='EVENT-TYPE'
```

As you can see, the problem is with the first query, because it does not include the discriminator value in the query. In the second query, it includes the discriminator in the query.
