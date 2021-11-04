select 
 effect_history.id, effect_history.effect_id,effect_history.effect_date, effect_history.user_med_id,
 user_medication.id, user_medication.user_id,
 effect_history.severity, effect_history.duration,
 effects.id, effects.label, effects.effectDescription
 from effect_history, user_medication, effects
 where (user_medication.id = effect_history.user_med_id) and
 (user_medication.user_id = ?) and 
 (effects.id = effect_history.effect_id)
 order by effect_history.effect_date;
 
 
