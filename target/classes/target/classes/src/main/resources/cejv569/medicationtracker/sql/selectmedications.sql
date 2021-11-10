select id, format_id, measurement_id, userid, brand_name, generic_name from medication where userid = ? order by (brand_name or generic_name) asc;




