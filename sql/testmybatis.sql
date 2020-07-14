select * from user where username like '%明%';
select * from user where username like concat('%','明','%');

select * from user where sex=1 and username like concat('%','明','%') and id in(15,20,25);

select * from user where sex=1 and username like concat('%','明','%') and (id=15 or id=20 or id=25);

-- 查询订单信息，关联查询创建订单的用户信息
select o.* , u.username , u.birthday , u.sex , u.address
from orders o , user u
where o.user_id = u.id;

-- 查询订单及订单明细的信息
select o.* , d.id did , d.items_id ,d.items_num
from orders o , orderdetail d
where o.id = d.orders_id;

-- 查询用户及用户购买商品信息
select u.* ,
			 o.id oid , o.number , o.createtime , o.note , 
			 d.id did , d.items_num ,
			 i.id iid , i.name , i.pic , i.price , i.createtime time , i.detail
from user u , orders o , orderdetail d , items i
where u.id = o.user_id and o.id = d.orders_id and d.items_id = i.id;