
    alter table fo_add_cost 
        drop 
        foreign key add_cost_provider_fk;

    alter table fo_dish 
        drop 
        foreign key dish_provider_fk;

    alter table fo_dish_comp 
        drop 
        foreign key dish_comp_dish_fk;

    alter table fo_dish_elem 
        drop 
        foreign key dish_elem_dish_comp_fk;

    alter table fo_dish_genere 
        drop 
        foreign key dish_gene_dish_fk;

    alter table fo_dish_genere 
        drop 
        foreign key dish_gene_gene_fk;

    alter table fo_email 
        drop 
        foreign key email_contact_fk;

    alter table fo_food_order 
        drop 
        foreign key food_order_person_fk;

    alter table fo_food_order 
        drop 
        foreign key food_order_provider_fk;

    alter table fo_food_order_group 
        drop 
        foreign key foodordgrp_foodord_fk;

    alter table fo_food_order_group 
        drop 
        foreign key foodordgrp_group_fk;

    alter table fo_group 
        drop 
        foreign key group_organization_fk;

    alter table fo_group 
        drop 
        foreign key group_person_fk;

    alter table fo_location 
        drop 
        foreign key location_organization_fk;

    alter table fo_order_item 
        drop 
        foreign key order_item_dish_fk;

    alter table fo_order_item 
        drop 
        foreign key order_item_food_ord_fk;

    alter table fo_order_item 
        drop 
        foreign key order_item_person_fk;

    alter table fo_order_sub_item 
        drop 
        foreign key ord_sub_item_dish_comp_fk;

    alter table fo_order_sub_item 
        drop 
        foreign key ord_sub_item_dish_elem_fk;

    alter table fo_order_sub_item 
        drop 
        foreign key ord_sub_item_ord_item_fk;

    alter table fo_person 
        drop 
        foreign key person_contact_fk;

    alter table fo_person 
        drop 
        foreign key person_location_fk;

    alter table fo_person_group 
        drop 
        foreign key person_group_group_fk;

    alter table fo_person_group 
        drop 
        foreign key person_group_person_fk;

    alter table fo_phone 
        drop 
        foreign key phone_contact_fk;

    alter table fo_provider 
        drop 
        foreign key provider_address_fk;

    alter table fo_provider 
        drop 
        foreign key provider_contact_fk;

    alter table fo_vacation 
        drop 
        foreign key vacation_provider_fk;

    alter table fo_web_url 
        drop 
        foreign key web_url_contact_fk;

    alter table fo_working_hour 
        drop 
        foreign key wrk_hour_provider_fk;

    drop table if exists fo_add_cost;

    drop table if exists fo_address;

    drop table if exists fo_contact;

    drop table if exists fo_dish;

    drop table if exists fo_dish_comp;

    drop table if exists fo_dish_elem;

    drop table if exists fo_dish_genere;

    drop table if exists fo_email;

    drop table if exists fo_food_order;

    drop table if exists fo_food_order_group;

    drop table if exists fo_genere;

    drop table if exists fo_group;

    drop table if exists fo_location;

    drop table if exists fo_order_item;

    drop table if exists fo_order_sub_item;

    drop table if exists fo_organization;

    drop table if exists fo_person;

    drop table if exists fo_person_group;

    drop table if exists fo_phone;

    drop table if exists fo_provider;

    drop table if exists fo_vacation;

    drop table if exists fo_web_url;

    drop table if exists fo_working_hour;

    drop table if exists seq_fo_add_cost_id;

    drop table if exists seq_fo_address_id;

    drop table if exists seq_fo_contact_id;

    drop table if exists seq_fo_dish_comp_id;

    drop table if exists seq_fo_dish_elem_id;

    drop table if exists seq_fo_dish_genere_id;

    drop table if exists seq_fo_dish_id;

    drop table if exists seq_fo_email_id;

    drop table if exists seq_fo_food_ord_grp_id;

    drop table if exists seq_fo_food_ord_id;

    drop table if exists seq_fo_genere_id;

    drop table if exists seq_fo_group_id;

    drop table if exists seq_fo_localization_id;

    drop table if exists seq_fo_ord_item_id;

    drop table if exists seq_fo_organization_id;

    drop table if exists seq_fo_person_group_id;

    drop table if exists seq_fo_person_id;

    drop table if exists seq_fo_phone_id;

    drop table if exists seq_fo_provider_id;

    drop table if exists seq_fo_sub_ord_item_id;

    drop table if exists seq_fo_vacation_id;

    drop table if exists seq_fo_web_url_id;

    drop table if exists seq_fo_working_hour_id;
