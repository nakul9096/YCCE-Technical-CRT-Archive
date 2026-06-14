use nakul_crt;
-- window functions perform calculations across a set of rows
-- that are related to the current rows, without collapsing the 
-- rows into a single result (unlike GROUP BY). alter

-- In short we can say, --> they give you aggregate-like
-- result while keeping each row visible.

-- Basic Syntax,
-- window_function (expression)
-- OVER (partition by column 
-- 			order by column )

-- common window function -->
-- Row_Number(), Rank(), Dense_Rank(), Sum(), avg(), lag(), lead(). 

-- ------------------------------------------------------------------------------------

-- ROW_NUMBER()
-- BASIC
-- Q1) Assign row number by salary (highest first)
-- ANS: SELECT emp_name, salary, row_number() over (order by salary desc) as rn from employees;

-- Q2) Row Number within each department
-- ANS: SELECT emp_name, dept, row_number() over (partition by dept order by salary) as rn from employees;
-- OR
-- ANS: 

-- Q3) Show joining within each department
-- ANS: SELECT emp_name, join_date, ROW_NUMBER() OVER ( ORDER BY join_date ) AS joining_order FROM employees;

-- Q4) Get latest joined employee in each department
-- WITH cte AS (
--     SELECT *,
--            ROW_NUMBER() OVER (
--                ORDER BY join_date DESC
--            ) AS rn
--     FROM employees
-- )
-- SELECT emp_id, emp_name, dept, salary, join_date
-- FROM cte
-- WHERE rn = 1;

-- Q5) 
-- WITH cte AS (
--     SELECT *,
--            DENSE_RANK() OVER (
--                PARTITION BY dept
--                ORDER BY salary DESC
--            ) AS rnk
--     FROM employees
-- )
-- SELECT emp_id,
--        emp_name,
--        dept,
--        salary
-- FROM cte
-- WHERE rnk <= 2
-- ORDER BY dept, salary DESC;

-- Q6) REMOVE DUPLICATE SALARIES INSIDE DEPARTMENT
-- SELECT *
-- FROM (
-- SELECT *, row_number() over(partition by dept,salary order by emp_id ) sar
-- FROM employees) x WHERE sar = 1;

-- Q7) 
-- SELECT *
-- FROM (
--     SELECT *,
--            ROW_NUMBER() OVER (
--                PARTITION BY dept
--                ORDER BY salary DESC
--            ) AS dr
--     FROM employees
-- ) x
-- WHERE dr = 3;

-- Q8) identify employees who joined after 2nd joiner in department
-- SELECT *
-- FROM (
--     SELECT *,
--            ROW_NUMBER() OVER (
--                PARTITION BY dept
--                ORDER BY join_date
--            ) AS rn
--     FROM employees
-- ) x
-- WHERE rn > 2;

-- -----------------------------------------------------------------------------------
-- RANK QUESTIONS
-- -----------------------------------------------------------------------------------

-- Q1) Rank employees by salary
SELECT emp_id,
       emp_name,
       dept,
       salary,
       RANK() OVER (ORDER BY salary DESC) AS rnk
FROM employees;

-- Q2) Rank department by total salary

-- SELECT dept,
--        total_salary,
--        RANK() OVER (ORDER BY total_salary DESC) AS dept_rank
-- FROM (
--     SELECT dept,
--            SUM(salary) AS total_salary
--     FROM employees
--     GROUP BY dept
-- ) x;

-- Q3) Find employees earning more than department average

-- SELECT *
-- FROM (
--     SELECT *,
--            AVG(salary) OVER (PARTITION BY dept) AS dept_avg
--     FROM employees
-- ) x
-- WHERE salary > dept_avg;

-- Q4) Departments where multiple emp share highest salary 
SELECT dept
FROM (
    SELECT dept,
           emp_id,
           salary,
           MAX(salary) OVER (PARTITION BY dept) AS max_sal
    FROM employees
) x
WHERE salary = max_sal
GROUP BY dept
HAVING COUNT(*) > 1;
-- ------------------------------------------------------------------------------------

-- create table employees(
-- emp_id INT,
-- emp_name varchar(50),
-- dept VARCHAR(20),
-- salary INT,
-- join_Date date);

-- ------------------------------------------------------------------------------------