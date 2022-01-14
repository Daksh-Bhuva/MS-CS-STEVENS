with month_sales as (
	select cust, prod, month, sum(quant) as month_sum
	from sales
	group by cust, prod, month
),

cumulative_sales as (
	select a.cust, a.prod, a.month, sum(b.month_sum) as cumulative_sum
	from month_sales as a, month_sales as b
	where a.cust = b.cust AND a.prod = b.prod AND a.month >= b.month
	group by a.cust, a.prod, a.month
),

final_sales as (
	select cust, prod, sum(quant)*0.75 as final_sum
	from sales
	group by cust, prod
)

select cumulative_sales.cust as "CUSTOMER", cumulative_sales.prod as "PRODUCT", 
		min(cumulative_sales.month) as "75% PURCHASED BY MONTH"
from cumulative_sales, final_sales
where cumulative_sales.cust = final_sales.cust and cumulative_sales.prod = final_sales.prod and cumulative_sum >= final_sum
group by cumulative_sales.cust, cumulative_sales.prod