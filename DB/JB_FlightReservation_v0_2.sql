use jb_flight_reservation;

alter table admin drop column admin_eno;

alter table airflight_info                        
change af_depart af_depart_time datetime not null,
change af_land af_land_time datetime not null;     

alter table airflight_info drop foreign key af_al_no;  

alter table airflight_info                          
add constraint af_al_no foreign key(al_no)          
references jb_flight_reservation.airline_info(al_no)
on update cascade on delete cascade;                                                              

alter table airflight_info       
modify af_depart_ap int unsigned not null,
modify af_land_ap int unsigned not null;  

alter table airflight_info             
alter column af_economy set default 0, 
alter column af_business set default 0;

alter table airflight_info                              
add constraint af_depart_ap_no foreign key(af_depart_ap)
references jb_flight_reservation.airport_info(ap_no)    
on update cascade on delete cascade,                    
add constraint af_land_ap_no foreign key(af_land_ap)    
references jb_flight_reservation.airport_info(ap_no)
on update cascade on delete cascade;    

alter table airline_info  
drop foreign key al_af_no;

alter table airline_info
drop column af_no;     

alter table airport_info 
drop foreign key ap_af_no,
drop column af_no;      

alter table coupon
drop foreign key cp_admin_no;

alter table coupon          
add constraint cp_admin_no foreign key(admin_no)  
references jb_flight_reservation.admin(admin_no)
on delete cascade on update cascade;

alter table customer      
drop foreign key ct_oc_no;

alter table customer                                 
add constraint ct_oc_no foreign key(oc_no)
references jb_flight_reservation.owned_coupon(oc_no)
on delete cascade on update cascade;

alter table inquiry      
drop foreign key i_ct_no;

alter table inquiry                              
add constraint i_ct_no foreign key(ct_no)        
references jb_flight_reservation.customer(ct_no)
on delete cascade on update cascade;

alter table inquiry_answer   
drop foreign key ia_admin_no,
drop foreign key ia_i_no;    

alter table inquiry_answer                      
add constraint ia_admin_no foreign key(admin_no)
references jb_flight_reservation.admin(admin_no)
on delete cascade on update cascade,            
add constraint ia_i_no foreign key(i_no)        
references jb_flight_reservation.inquiry(i_no)  
on delete cascade on update cascade;     

alter table owned_coupon  
drop foreign key oc_cp_no;

alter table owned_coupon                      
add constraint oc_cp_no foreign key(cp_no)    
references jb_flight_reservation.coupon(cp_no)
on delete cascade on update cascade;          

alter table price     
drop foreign key pr_pg;

alter table price                                     
add constraint pr_pg_no foreign key(pg_no)            
references jb_flight_reservation.passenger_info(pg_no)
on delete cascade on update cascade;                  

alter table reservation   
drop foreign key rv_ct_no;

alter table reservation                         
add constraint rv_ct_no foreign key(ct_no)      
references jb_flight_reservation.customer(ct_no)
on delete cascade on update cascade;            

alter table reserve_airflight
drop foreign key rv_af_af_no,
drop foreign key rv_af_rv_no;

alter table reserve_airflight                         
add constraint rv_af_af_no foreign key(af_no)         
references jb_flight_reservation.airflight_info(af_no)
on delete cascade on update cascade,                  
add constraint rv_af_rv_no foreign key(rv_no)         
references jb_flight_reservation.reservation(rv_no)   
on delete cascade on update cascade;                  

alter table reserve_passenger
drop foreign key rv_pg_pg_no,
drop foreign key rv_pg_rv_no;

alter table reserve_passenger                         
add constraint rv_pg_pg_no foreign key(pg_no)         
references jb_flight_reservation.passenger_info(pg_no)
on delete cascade on update cascade,                  
add constraint rv_pg_rv_no foreign key(rv_no)         
references jb_flight_reservation.reservation(rv_no)   
on delete cascade on update cascade;                  

alter table passenger_info
change pg_birth pg_birth date not null;

alter table reservation
drop column rv_pet_accompanying;

alter table reservation
add column rv_seat_grade varchar(45) not null;

alter table customer
drop constraint ct_oc_no;

alter table customer
drop oc_no;

alter table owned_coupon
add column ct_no int unsigned not null;

alter table owned_coupon
add constraint oc_ct_no foreign key(ct_no)
references jb_flight_reservation.customer(ct_no)
on update cascade on delete cascade;

alter table customer
modify column ct_pw varchar(100);