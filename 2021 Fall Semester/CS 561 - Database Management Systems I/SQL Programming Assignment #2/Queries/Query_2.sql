with base as (
	select cust, prod, state
 	from sales 
 	group by cust, prod, state
), 

Q1 as (
	select cust, prod, state, round(avg(quant)) as quant 
 	from sales where month between 1 and 3 
 	group by cust, prod, state
), 

Q2 as (
	select cust, prod, state, round(avg(quant)) as quant 
 	from sales where month between 4 and 6 
 	group by cust, prod, state
),

Q3 as (
	select cust, prod, state, round(avg(quant)) as quant 
 	from sales where month between 7 and 9 
 	group by cust, prod, state
), 

Q4 as (
	select cust, prod, state, round(avg(quant)) as quant 
 	from sales where month between 10 and 12 
 	group by cust, prod, state
), 

bef_aft_q1 as ( 
	select b.cust, b.prod, b.state, cast('1' as int) as "Q", cast(null as numeric) as before_avg, Q2.quant as after_avg 
 	from base as b left join Q2 
 	using(cust, prod, state)
), 

bef_aft_q2 as (
	select b.cust, b.prod, b.state, cast('2' as int) as "Q", Q1.quant as before_avg, Q3.quant as after_avg 
 	from base as b left join Q1 
 	using(cust, prod, state) 
 	left join Q3 
 	using(cust, prod, state)
), 

bef_aft_q3 as (
	select b.cust, b.prod, b.state, cast('3' as int) as "Q", Q2.quant as before_avg, Q4.quant as after_avg 
 	from base as b left join Q2 
 	using(cust, prod, state) 
 	left join Q4 
 	using(cust, prod, state)
), 

bef_aft_q4 as (
	select b.cust, b.prod, b.state, cast('4' as int) as "Q", Q3.quant as before_avg, cast(null as numeric) as after_avg 
 	from base as b left join Q3 
 	using(cust, prod, state)
),

final as (
	select * from bef_aft_q1 
	union 
  	select * from bef_aft_q2 
 	union 
 	select * from bef_aft_q3 
 	union 
	select * from bef_aft_q4
)

select cust "CUSTOMER", prod "PRODUCT", state "STATE", "Q", before_avg "BEFORE_AVG", after_avg "AFTER_AVG"
from final 