create or replace PROCEDURE actualizar_nombre_apellido(cedula varchar2, nombre varchar2, apellido varchar2)
IS
-- Declaracion de variables locales
BEGIN

UPDATE persona SET pers_nombre = nombre, pers_apellido = apellido, pers_fecha_creacion = SYSDATE WHERE pers_cedula = cedula;
commit;

END actualizar_nombre_apellido;
/

create or replace PROCEDURE obtener_NombreCompleto(cedula varchar2,  nombre OUT varchar2 )
IS
-- Declaracion de variables locales
BEGIN

Select pers_nombre || ' ' || pers_apellido into nombre from persona where pers_cedula = cedula;

END obtener_NombreCompleto;
/