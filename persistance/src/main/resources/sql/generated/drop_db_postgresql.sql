
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

    alter table fo_email 
        drop constraint email_contact_fk;

    alter table fo_food_order 
        drop constraint food_order_person_fk;

    alter table fo_food_order 
        drop constraint food_order_provider_fk;

    alter table fo_food_order_group 
        drop constraint foodordgrp_foodord_fk;

    alter table fo_food_order_group 
        drop constraint foodordgrp_group_fk;

    alter table fo_group 
        drop constraint group_organization_fk;

    alter table fo_group 
        drop constraint group_person_fk;

    alter table fo_location 
        drop constraint location_organization_fk;

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

    alter table fo_person 
        drop constraint person_contact_fk;

    alter table fo_person 
        drop constraint person_location_fk;

    alter table fo_person_group 
        drop constraint person_group_group_fk;

    alter table fo_person_group 
        drop constraint person_group_person_fk;

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

    drop table if exists fo_add_cost cascade;

    drop table if exists fo_address cascade;

    drop table if exists fo_contact cascade;

    drop table if exists fo_dish cascade;

    drop table if exists fo_dish_comp cascade;

    drop table if exists fo_dish_elem cascade;

    drop table if exists fo_dish_genere cascade;

    drop table if exists fo_email cascade;

    drop table if exists fo_food_order cascade;

    drop table if exists fo_food_order_group cascade;

    drop table if exists fo_genere cascade;

    drop table if exists fo_group cascade;

    drop table if exists fo_location cascade;

    drop table if exists fo_order_item cascade;

    drop table if exists fo_order_sub_item cascade;

    drop table if exists fo_organization cascade;

    drop table if exists fo_person cascade;

    drop table if exists fo_person_group cascade;

    drop table if exists fo_phone cascade;

    drop table if exists fo_provider cascade;

    drop table if exists fo_vacation cascade;

    drop table if exists fo_web_url cascade;

    drop table if exists fo_working_hour cascade;

    drop sequence seq_fo_add_cost_id;

    drop sequence seq_fo_address_id;

    drop sequence seq_fo_contact_id;

    drop sequence seq_fo_dish_comp_id;

    drop sequence seq_fo_dish_elem_id;

    drop sequence seq_fo_dish_genere_id;

    drop sequence seq_fo_dish_id;

    drop sequence seq_fo_email_id;

    drop sequence seq_fo_food_ord_grp_id;

    drop sequence seq_fo_food_ord_id;

    drop sequence seq_fo_genere_id;

    drop sequence seq_fo_group_id;

    drop sequence seq_fo_localization_id;

    drop sequence seq_fo_ord_item_id;

    drop sequence seq_fo_organization_id;

    drop sequence seq_fo_person_group_id;

    drop sequence seq_fo_person_id;

    drop sequence seq_fo_phone_id;

    drop sequence seq_fo_provider_id;

    drop sequence seq_fo_sub_ord_item_id;

    drop sequence seq_fo_vacation_id;

    drop sequence seq_fo_web_url_id;

    drop sequence seq_fo_working_hour_id;
