with base as(
	select cust, prod, month, state, round(avg(quant)) as cust_avg
	from sales
	group by cust, prod, month, state
),

other_prod as(
	select s1.cust, s1.prod, s1.month, s1.state, round(avg(s2.quant)) as prod_avg
	from sales s1, sales s2
	where s1.cust = s2.cust and s1.prod != s2.prod and s1.month = s2.month and s1.state = s2.state
	group by s1.cust, s1.prod, s1.month, s1.state
),

other_month as(
	select s1.cust, s1.prod, s1.month, s1.state, round(avg(s2.quant)) as month_avg
	from sales s1, sales s2
	where s1.cust = s2.cust and s1.prod = s2.prod and s1.month != s2.month and s1.state = s2.state
	group by s1.cust, s1.prod, s1.month, s1.state
),

other_state as(
	select s1.cust, s1.prod, s1.month, s1.state, round(avg(s2.quant)) as state_avg
	from sales s1, sales s2
	where s1.cust = s2.cust and s1.prod = s2.prod and s1.month = s2.month and s1.state != s2.state
	group by s1.cust, s1.prod, s1.month, s1.state
)

select base.cust as "CUSTOMER", base.prod as "PRODUCT", base.month as "MONTH", base.state as "STATE", 
       base.cust_avg as "CUST_AVG", other_prod.prod_avg as "OTHER_PROD_AVG", other_month.month_avg as "OTHER_MONTH_AVG", 
	   other_state.state_avg as "OTHER_STATE_AVG"
from base join other_prod 
using(cust,prod,state,month)
join other_month
using(cust,prod,state,month)
join other_state
using(cust,prod,state,month)