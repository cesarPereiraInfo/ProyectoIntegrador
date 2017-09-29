use farmacia;
create view s1 as( select pedido.idPedido, pedido.fechaHoraPedido, concat(persona.nombre,' ',persona.apePaterno,' ',persona.apeMaterno), 
producto.nombreGeneral, concat(detallePedido.precioCompra*detallePedido.cantidad), sucursal.nombre, detallePedido.cantidad from persona inner join
 empleado on persona.idPersona=empleado.idPersona inner join pedido on empleado.idEmpleado=pedido.idEmpleado 
 inner join sucursal on sucursal.idSucursal=pedido.idSucursal inner join detallePedido on pedido.idPedido=detallePedido.idPedido 
 inner join producto on producto.idProducto=detallePedido.idProducto where sucursal.idSucursal=1 and pedido.activo=1);

 select*from s1;
 
 create view s2 as(select pedido.idPedido, pedido.fechaHoraPedido, concat(persona.nombre,' ',persona.apePaterno,' ',persona.apeMaterno), 
producto.nombreGeneral, concat(detallePedido.precioCompra*detallePedido.cantidad), sucursal.nombre, detallePedido.cantidad from persona inner join
 empleado on persona.idPersona=empleado.idPersona inner join pedido on empleado.idEmpleado=pedido.idEmpleado 
 inner join sucursal on sucursal.idSucursal=pedido.idSucursal inner join detallePedido on pedido.idPedido=detallePedido.idPedido 
 inner join producto on producto.idProducto=detallePedido.idProducto where sucursal.idSucursal=2 and pedido.activo=1);
 
 select*from s2;
 
 create view s3 as(select pedido.idPedido, pedido.fechaHoraPedido, concat(persona.nombre,' ',persona.apePaterno,' ',persona.apeMaterno), 
producto.nombreGeneral, concat(detallePedido.precioCompra*detallePedido.cantidad), sucursal.nombre, detallePedido.cantidad from persona inner join
 empleado on persona.idPersona=empleado.idPersona inner join pedido on empleado.idEmpleado=pedido.idEmpleado 
 inner join sucursal on sucursal.idSucursal=pedido.idSucursal inner join detallePedido on pedido.idPedido=detallePedido.idPedido 
 inner join producto on producto.idProducto=detallePedido.idProducto where sucursal.idSucursal=3 and pedido.activo=1);
 
 select*from s3;
 
  create view s4 as(select pedido.idPedido, pedido.fechaHoraPedido, concat(persona.nombre,' ',persona.apePaterno,' ',persona.apeMaterno), 
producto.nombreGeneral, concat(detallePedido.precioCompra*detallePedido.cantidad), sucursal.nombre, detallePedido.cantidad from persona inner join
 empleado on persona.idPersona=empleado.idPersona inner join pedido on empleado.idEmpleado=pedido.idEmpleado 
 inner join sucursal on sucursal.idSucursal=pedido.idSucursal inner join detallePedido on pedido.idPedido=detallePedido.idPedido 
 inner join producto on producto.idProducto=detallePedido.idProducto where sucursal.idSucursal=4 and pedido.activo=1);
 
 select*from s4;
 
 create view venta1 as (select venta.idVenta,venta.fechaHora,concat(persona.nombre,' ',persona.apePaterno,' ',persona.apeMaterno),
 producto.nombreGeneral ,detalleVenta.cantidad,concat(detalleVenta.precioVenta*detalleVenta.cantidad),cliente.idCliente, sucursal.nombre 
 from empleado inner join venta on empleado.idEmpleado=venta.idEmpleado inner join cliente on cliente.idCliente
 =venta.idCliente inner join detalleVenta on venta.idVenta=detalleVenta.idVenta inner join producto on 
 producto.idProducto=detalleVenta.idProducto inner join sucursal on sucursal.idSucursal=venta.idSucursal inner join
 persona on persona.idPersona=empleado.idPersona where sucursal.idSucursal=1 and venta.activo=1);
 
 select*from venta1;
 
  create view venta2 as (select venta.idVenta,venta.fechaHora,concat(persona.nombre,' ',persona.apePaterno,' ',persona.apeMaterno),
 producto.nombreGeneral ,detalleVenta.cantidad,concat(detalleVenta.precioVenta*detalleVenta.cantidad),cliente.idCliente, sucursal.nombre 
 from empleado inner join venta on empleado.idEmpleado=venta.idEmpleado inner join cliente on cliente.idCliente
 =venta.idCliente inner join detalleVenta on venta.idVenta=detalleVenta.idVenta inner join producto on 
 producto.idProducto=detalleVenta.idProducto inner join sucursal on sucursal.idSucursal=venta.idSucursal inner join
 persona on persona.idPersona=empleado.idPersona where sucursal.idSucursal=2 and venta.activo=1);
 
 select*from venta2;
 
   create view venta3 as (select venta.idVenta,venta.fechaHora,concat(persona.nombre,' ',persona.apePaterno,' ',persona.apeMaterno),
 producto.nombreGeneral ,detalleVenta.cantidad,concat(detalleVenta.precioVenta*detalleVenta.cantidad),cliente.idCliente, sucursal.nombre 
 from empleado inner join venta on empleado.idEmpleado=venta.idEmpleado inner join cliente on cliente.idCliente
 =venta.idCliente inner join detalleVenta on venta.idVenta=detalleVenta.idVenta inner join producto on 
 producto.idProducto=detalleVenta.idProducto inner join sucursal on sucursal.idSucursal=venta.idSucursal inner join
 persona on persona.idPersona=empleado.idPersona where sucursal.idSucursal=3 and venta.activo=1);
 
 select*from venta3;
 
   create view venta4 as (select venta.idVenta,venta.fechaHora,concat(persona.nombre,' ',persona.apePaterno,' ',persona.apeMaterno),
 producto.nombreGeneral ,detalleVenta.cantidad,concat(detalleVenta.precioVenta*detalleVenta.cantidad),cliente.idCliente, sucursal.nombre 
 from empleado inner join venta on empleado.idEmpleado=venta.idEmpleado inner join cliente on cliente.idCliente
 =venta.idCliente inner join detalleVenta on venta.idVenta=detalleVenta.idVenta inner join producto on 
 producto.idProducto=detalleVenta.idProducto inner join sucursal on sucursal.idSucursal=venta.idSucursal inner join
 persona on persona.idPersona=empleado.idPersona where sucursal.idSucursal=4 and venta.activo=1);
 
 select*from venta4;
 
 
 
 select*from detalleventa;
 -- Pedidos
 
 select pedido.idPedido, pedido.fechaHoraPedido, concat(persona.nombre,' ',persona.apePaterno,' ',persona.apeMaterno),
 producto.nombreGeneral, concat(detallepedido.precioCompra*detallepedido.cantidad), sucursal.nombre, detallepedido.cantidad 
 from persona inner join empleado on persona.idPersona=empleado.idPersona inner join pedido on empleado.idEmpleado=pedido.idEmpleado inner join sucursal 
 on sucursal.idSucursal=pedido.idSucursal inner join detallepedido on pedido.idPedido=detallepedido.idPedido inner join producto on producto.idProducto=
 detallepedido.idProducto where pedido.activo=1 order by pedido.idPedido;
 
 
 -- Ventas
 
 select venta.idVenta,venta.fechaHora,concat(persona.nombre,' ',persona.apePaterno,' ',persona.apeMaterno),
 producto.nombreGeneral ,detalleventa.cantidad,concat(detalleventa.precioVenta*detalleventa.cantidad),
 cliente.idCliente, sucursal.nombre  from empleado inner join venta on empleado.idEmpleado=venta.idEmpleado 
 inner join cliente on cliente.idCliente=venta.idCliente inner join detalleventa on venta.idVenta=detalleventa.idVenta 
 inner join producto on producto.idProducto=detalleventa.idProducto inner join sucursal on sucursal.idSucursal=venta.idSucursal 
 inner join persona on persona.idPersona=empleado.idPersona where venta.activo=1 order by venta.idVenta;
 
 
 
 
 
 
 use farmacia;

delimiter $$
create trigger insertarPedido
after insert on detallePedido
for each row
begin
	update producto
    set unidadesEnvase = unidadesEnvase + new.cantidad /* se entra a los atributos del encabezado con "new" */
	where idProducto=new.idProducto;
end
$$

create trigger insertarVenta
after insert on detalleVenta
for each row
begin
	update producto
    set unidadesEnvase = unidadesEnvase - new.cantidad
	where idProducto=new.idProducto;
end
$$



delimiter $$
 create procedure codigoEmpleado(
 in fecha_ingreso date,
 out codigo varchar(8))
 begin
 declare año varchar(4);
 declare mes varchar(7);
 declare comodin1 varchar(2);
 declare comodin2 varchar(2);
 declare comodin3 varchar(4);
 declare comodin4 varchar(4);
 declare i int;
 declare final varchar(4);
 set año=left(fecha_ingreso,4);
 set comodin1=right(año,2);
 set mes=left(fecha_ingreso,7);
 set comodin2=right(mes,2);
 select count(idUsuario) into comodin3 from usuario;
 set comodin3=comodin3+1;
 set comodin4=length(comodin3);
 set i=0;
 set final=0;
 if comodin4 = 1 then
 set final="000";
 end if;
 if comodin4 = 2 then
 set final="00";
 end if;
  if comodin4 = 3 then
 set final="0";
 end if;
  if comodin4 = 4 then
 set final="";
 end if;
 set final=concat(final,comodin3);
 set codigo=concat(comodin1,comodin2,final);
 set i=i+1;
 end$$
 
 call codigoEmpleado ('2017-08-06',@res)$$
 select @res $$
 
