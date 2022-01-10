with OVERALL as(
	select prod, cust, avg(quant) as Average, sum(quant) as Total, count(quant) as Count
	from sales
	where year between 2016 and 2020
	group by prod, cust
),

CT as(
	select prod, cust, avg(quant) as CT_AVG
	from sales
	where state =  'CT'
	group by prod, cust
),

NY as(
	select prod, cust, avg(quant) as NY_AVG
	from sales
	where state =  'NY'
	group by prod, cust
),

NJ as(
	select prod, cust, avg(quant) as NJ_AVG
	from sales
	where state =  'NJ'
	group by prod, cust
),

PA as(
	select prod, cust, avg(quant) as PA_AVG
	from sales
	where state =  'PA'
	group by prod, cust
)

select OVERALL.prod as Product, OVERALL.cust as Customer, 
		round(CT.CT_AVG) as CT_AVG, round(NY.NY_AVG) as NY_AVG, round(NJ.NJ_AVG) as NJ_AVG, round(PA.PA_AVG) as PA_AVG, 
		round(OVERALL.Average) as Average, OVERALL.Total, OVERALL.Count
from OVERALL
join CT
on OVERALL.cust = CT.cust and OVERALL.prod = CT.prod
join NY
on OVERALL.cust = NY.cust and OVERALL.prod = NY.prod
join NJ
on OVERALL.cust = NJ.cust and OVERALL.prod = NJ.prod
join PA 
on OVERALL.cust = PA.cust and OVERALL.prod = PA.prod
group by PRODUCT, CUSTOMER, CT.CT_AVG, NY.NY_AVG, NJ.NJ_AVG, PA.PA_AVG, Average, Total, Count