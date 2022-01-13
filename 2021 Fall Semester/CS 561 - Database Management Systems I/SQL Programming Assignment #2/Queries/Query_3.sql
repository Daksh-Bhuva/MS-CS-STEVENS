with base as (
	select s.prod, s.quant, (select count(*) 
							 from sales 
							 where quant <= s.quant and prod = s.prod) as count
	from sales s
),

middle as (
	select base.prod, (max(count)/2 + 1) as mid_point
	from base
	group by prod
),

final as (
	select base.prod, base.quant
	from base, middle
	where base.prod = middle.prod and base.count >= middle.mid_point
)

select final.prod as "PRODUCT", min(final.quant) as "MEDIAN QUANT"
from final
group by prod