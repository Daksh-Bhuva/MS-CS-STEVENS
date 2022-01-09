with SUM_OF_SALES as(
	select prod, month, sum(quant) as SUM_Q
	from sales
	group by month, prod
),

MIN_MAX as(
	select SUM_OF_SALES.prod, max(SUM_OF_SALES.SUM_Q) as MOST_TOTAL_Q, min(SUM_OF_SALES.SUM_Q) as LEAST_TOTAL_Q
	from SUM_OF_SALES
	group by SUM_OF_SALES.prod
),

MOST_FAV as(
	select SUM_OF_SALES.prod, SUM_OF_SALES.month
	from SUM_OF_SALES join MIN_MAX
	on SUM_OF_SALES.prod = MIN_MAX.prod and SUM_OF_SALES.SUM_Q = MIN_MAX.MOST_TOTAL_Q
),

LEAST_FAV as(
	select SUM_OF_SALES.prod, SUM_OF_SALES.month
	from SUM_OF_SALES join MIN_MAX
	on SUM_OF_SALES.prod = MIN_MAX.prod and SUM_OF_SALES.SUM_Q = MIN_MAX.LEAST_TOTAL_Q
)

select MOST_FAV.prod as PRODUCT, MOST_FAV.month as MOST_FAV_MO, LEAST_FAV.month as LEAST_FAV_MO
from MOST_FAV join LEAST_FAV
on MOST_FAV.prod =LEAST_FAV.prod
order by PRODUCT