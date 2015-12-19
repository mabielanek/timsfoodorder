
    alter table fo_add_cost 
        drop constraint add_cost_provider_fk;

    alter table fo_dish 
        drop constraint dish_provider_fk;

    alter table fo_dish_comp 
        drop constraint dish_comp_dish_fk;

    alter table fo_dish_elem 
        drop constraint dish_elem_dish_comp_fk;

    alter table fo_dish_genere 
        drop constraint dish_gene_dish_fk;

    alter table fo_dish_genere 
        drop constraint dish_gene_gene_fk;

    alter table fo_dish_price 
        drop constraint dish_price_dish_fk;

    alter table fo_dish_price 
        drop constraint dish_price_dish_comp_fk;

    alter table fo_dish_price 
        drop constraint dish_price_dish_elem_fk;

    alter table fo_email 
        drop constraint email_contact_fk;

    alter table fo_food_order 
        drop constraint food_order_person_fk;

    alter table fo_food_order 
        drop constraint food_order_provider_fk;

    alter table fo_order_item 
        drop constraint order_item_dish_fk;

    alter table fo_order_item 
        drop constraint order_item_food_ord_fk;

    alter table fo_order_item 
        drop constraint order_item_person_fk;

    alter table fo_order_sub_item 
        drop constraint ord_sub_item_dish_comp_fk;

    alter table fo_order_sub_item 
        drop constraint ord_sub_item_dish_elem_fk;

    alter table fo_order_sub_item 
        drop constraint ord_sub_item_ord_item_fk;

    alter table fo_phone 
        drop constraint phone_contact_fk;

    alter table fo_provider 
        drop constraint provider_address_fk;

    alter table fo_provider 
        drop constraint provider_contact_fk;

    alter table fo_vacation 
        drop constraint vacation_provider_fk;

    alter table fo_web_url 
        drop constraint web_url_contact_fk;

    alter table fo_working_hour 
        drop constraint wrk_hour_provider_fk;

    drop table fo_add_cost if exists;

    drop table fo_address if exists;

    drop table fo_contact if exists;

    drop table fo_dish if exists;

    drop table fo_dish_comp if exists;

    drop table fo_dish_elem if exists;

    drop table fo_dish_genere if exists;

    drop table fo_dish_price if exists;

    drop table fo_email if exists;

    drop table fo_food_order if exists;

    drop table fo_genere if exists;

    drop table fo_order_item if exists;

    drop table fo_order_sub_item if exists;

    drop table fo_person if exists;

    drop table fo_phone if exists;

    drop table fo_provider if exists;

    drop table fo_vacation if exists;

    drop table fo_web_url if exists;

    drop table fo_working_hour if exists;

    drop sequence seq_fo_add_cost_id;

    drop sequence seq_fo_address_id;

    drop sequence seq_fo_contact_id;

    drop sequence seq_fo_dish_comp_id;

    drop sequence seq_fo_dish_elem_id;

    drop sequence seq_fo_dish_genere_id;

    drop sequence seq_fo_dish_id;

    drop sequence seq_fo_dish_price_id;

    drop sequence seq_fo_email_id;

    drop sequence seq_fo_food_ord_id;

    drop sequence seq_fo_genere_id;

    drop sequence seq_fo_ord_item_id;

    drop sequence seq_fo_person_id;

    drop sequence seq_fo_phone_id;

    drop sequence seq_fo_provider_id;

    drop sequence seq_fo_sub_ord_item_id;

    drop sequence seq_fo_vacation_id;

    drop sequence seq_fo_web_url_id;

    drop sequence seq_fo_working_hour_id;
