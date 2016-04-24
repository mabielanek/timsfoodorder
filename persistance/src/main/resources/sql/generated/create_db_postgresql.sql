
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

    create table fo_add_cost (
        id int8 not null,
        cost numeric(19, 2),
        kind varchar(15) not null,
        last_modification_id int8,
        status varchar(15) not null,
        provider_id int8,
        primary key (id),
        check (status IN('ACTIVE','INACTIVE','DELETED') and kind IN('MINVAL','DELIVERY','PACK'))
    );

    create table fo_address (
        id int8 not null,
        address1 varchar(255),
        address2 varchar(255),
        city varchar(255),
        comment_text varchar(1024),
        country varchar(255),
        display_index int4 not null,
        last_modification_id int8,
        state varchar(40),
        status varchar(15) not null,
        zip_code varchar(15),
        primary key (id),
        check (status IN('ACTIVE','INACTIVE','DELETED'))
    );

    create table fo_contact (
        id int8 not null,
        last_modification_id int8,
        status varchar(15) not null,
        primary key (id),
        check (status IN('ACTIVE','INACTIVE','DELETED'))
    );

    create table fo_dish (
        id int8 not null,
        end_day timestamp not null,
        start_day timestamp not null,
        description varchar(255),
        last_modification_id int8,
        name varchar(255),
        price numeric(19, 2),
        status varchar(15) not null,
        provider_id int8,
        primary key (id),
        check (status IN('ACTIVE','INACTIVE','DELETED'))
    );

    create table fo_dish_comp (
        id int8 not null,
        description varchar(255),
        last_modification_id int8,
        max_elements int4 not null,
        elements_required int4 not null,
        price numeric(19, 2),
        status varchar(15) not null,
        use_as_dish_price varchar(1) not null,
        dish_id int8,
        primary key (id),
        check (status IN('ACTIVE','INACTIVE','DELETED') and use_as_dish_price IN('Y','N'))
    );

    create table fo_dish_elem (
        id int8 not null,
        description varchar(255),
        last_modification_id int8,
        name varchar(255),
        price numeric(19, 2),
        status varchar(15) not null,
        dish_comp_id int8,
        primary key (id),
        check (status IN('ACTIVE','INACTIVE','DELETED'))
    );

    create table fo_dish_genere (
        id int8 not null,
        last_modification_id int8,
        dish_id int8,
        genere_id int8,
        primary key (id)
    );

    create table fo_email (
        id int8 not null,
        comment_text varchar(1024),
        display_index int4 not null,
        email_address varchar(255) not null,
        last_modification_id int8,
        status varchar(15) not null,
        contact_id int8,
        primary key (id),
        check (status IN('ACTIVE','INACTIVE','DELETED'))
    );

    create table fo_food_order (
        id int8 not null,
        last_modification_id int8,
        add_time timestamp not null,
        order_status varchar(15) not null,
        order_time timestamp not null,
        person_id int8,
        provider_id int8,
        primary key (id),
        check (orderStatus IN('ACTIVE','CANCELLED','DELIVERED','CLOSED'))
    );

    create table fo_food_order_group (
        id int8 not null,
        last_modification_id int8,
        foodOrder int8,
        group int8,
        primary key (id)
    );

    create table fo_genere (
        id int8 not null,
        last_modification_id int8,
        name varchar(255),
        primary key (id)
    );

    create table fo_group (
        id int8 not null,
        last_modification_id int8,
        name varchar(255) not null,
        organization_owner int8,
        person_owner int8,
        primary key (id)
    );

    create table fo_location (
        id int8 not null,
        last_modification_id int8,
        name varchar(255) not null,
        organization_id int8,
        primary key (id)
    );

    create table fo_order_item (
        id int8 not null,
        count int4 not null,
        last_modification_id int8,
        price numeric(19, 2),
        dish_id int8,
        food_order_id int8,
        person_id int8,
        primary key (id)
    );

    create table fo_order_sub_item (
        id int8 not null,
        last_modification_id int8,
        dish_comp_id int8,
        dish_elem_id int8,
        order_item_id int8,
        primary key (id)
    );

    create table fo_organization (
        id int8 not null,
        last_modification_id int8,
        name varchar(255) not null,
        primary key (id)
    );

    create table fo_person (
        id int8 not null,
        desk varchar(15),
        last_modification_id int8,
        login varchar(255) not null,
        password varchar(255) not null,
        room varchar(15),
        contact_id int8,
        location_id int8,
        primary key (id)
    );

    create table fo_person_group (
        id int8 not null,
        last_modification_id int8,
        group int8,
        person int8,
        primary key (id)
    );

    create table fo_phone (
        id int8 not null,
        comment_text varchar(1024),
        display_index int4 not null,
        last_modification_id int8,
        number_type varchar(15) not null,
        phone varchar(15),
        phone_ext varchar(15),
        status varchar(15) not null,
        contact_id int8,
        primary key (id),
        check (number_type IN('MOBILE','FAX','LANDLINE') and status IN('ACTIVE','INACTIVE','DELETED'))
    );

    create table fo_provider (
        id int8 not null,
        comment varchar(255) not null,
        last_modification_id int8,
        name varchar(255) not null,
        status varchar(15) not null,
        address_id int8,
        contact_id int8,
        primary key (id),
        check (status IN('ACTIVE','INACTIVE','DELETED'))
    );

    create table fo_vacation (
        owner_type varchar(15) not null,
        id int8 not null,
        end_day timestamp not null,
        last_modification_id int8,
        start_day timestamp not null,
        provider_id int8,
        primary key (id)
    );

    create table fo_web_url (
        id int8 not null,
        comment_text varchar(1024),
        display_index int4 not null,
        last_modification_id int8,
        status varchar(15) not null,
        web_url_address varchar(255) not null,
        contact_id int8,
        primary key (id),
        check (status IN('ACTIVE','INACTIVE','DELETED'))
    );

    create table fo_working_hour (
        owner_type varchar(15) not null,
        id int8 not null,
        end_time timestamp not null,
        last_modification_id int8,
        start_time timestamp not null,
        week_day varchar(15) not null,
        provider_id int8,
        primary key (id)
    );

    create index idx_add_cost_provider_fk on fo_add_cost (provider_id);

    create index idx_dish_provider_fk on fo_dish (provider_id);

    create index idx_dish_comp_dish_fk on fo_dish_comp (dish_id);

    create index idx_dish_elem_dish_comp_fk on fo_dish_elem (dish_comp_id);

    create index idx_dish_gene_dish_fk on fo_dish_genere (dish_id);

    create index idx_dish_gene_gene_fk on fo_dish_genere (genere_id);

    create index idx_contact_email_fk on fo_email (contact_id);

    create index idx_food_order_person_fk on fo_food_order (person_id);

    create index idx_food_order_provider_fk on fo_food_order (provider_id);

    create index idx_order_time on fo_food_order (order_time);

    create index idx_order_item_person_fk on fo_order_item (person_id);

    create index idx_order_item_food_ord_fk on fo_order_item (food_order_id);

    create index idx_order_item_dish_fk on fo_order_item (dish_id);

    create index idx_ord_sub_item_ord_item_fk on fo_order_sub_item (order_item_id);

    create index idx_ord_subitem_dish_comp_fk on fo_order_sub_item (dish_comp_id);

    create index idx_ord_subitem_dish_elem_fk on fo_order_sub_item (dish_elem_id);

    create index idx_contact_phone_fk on fo_phone (contact_id);

    create index idx_provider_name on fo_provider (name);

    create index idx_provider_address_fk on fo_provider (address_id);

    create index idx_provider_contact_fk on fo_provider (contact_id);

    create index idx_vacation_provider_fk on fo_vacation (provider_id);

    create index idx_contact_web_url_fk on fo_web_url (contact_id);

    create index idx_wrk_hour_provider_fk on fo_working_hour (provider_id);

    alter table fo_add_cost 
        add constraint add_cost_provider_fk 
        foreign key (provider_id) 
        references fo_provider;

    alter table fo_dish 
        add constraint dish_provider_fk 
        foreign key (provider_id) 
        references fo_provider;

    alter table fo_dish_comp 
        add constraint dish_comp_dish_fk 
        foreign key (dish_id) 
        references fo_dish;

    alter table fo_dish_elem 
        add constraint dish_elem_dish_comp_fk 
        foreign key (dish_comp_id) 
        references fo_dish_comp;

    alter table fo_dish_genere 
        add constraint dish_gene_dish_fk 
        foreign key (dish_id) 
        references fo_dish;

    alter table fo_dish_genere 
        add constraint dish_gene_gene_fk 
        foreign key (genere_id) 
        references fo_genere;

    alter table fo_email 
        add constraint email_contact_fk 
        foreign key (contact_id) 
        references fo_contact;

    alter table fo_food_order 
        add constraint food_order_person_fk 
        foreign key (person_id) 
        references fo_person;

    alter table fo_food_order 
        add constraint food_order_provider_fk 
        foreign key (provider_id) 
        references fo_provider;

    alter table fo_food_order_group 
        add constraint foodordgrp_foodord_fk 
        foreign key (foodOrder) 
        references fo_food_order;

    alter table fo_food_order_group 
        add constraint foodordgrp_group_fk 
        foreign key (group) 
        references fo_group;

    alter table fo_group 
        add constraint group_organization_fk 
        foreign key (organization_owner) 
        references fo_organization;

    alter table fo_group 
        add constraint group_person_fk 
        foreign key (person_owner) 
        references fo_person;

    alter table fo_location 
        add constraint location_organization_fk 
        foreign key (organization_id) 
        references fo_organization;

    alter table fo_order_item 
        add constraint order_item_dish_fk 
        foreign key (dish_id) 
        references fo_dish;

    alter table fo_order_item 
        add constraint order_item_food_ord_fk 
        foreign key (food_order_id) 
        references fo_food_order;

    alter table fo_order_item 
        add constraint order_item_person_fk 
        foreign key (person_id) 
        references fo_person;

    alter table fo_order_sub_item 
        add constraint ord_sub_item_dish_comp_fk 
        foreign key (dish_comp_id) 
        references fo_dish_comp;

    alter table fo_order_sub_item 
        add constraint ord_sub_item_dish_elem_fk 
        foreign key (dish_elem_id) 
        references fo_dish_elem;

    alter table fo_order_sub_item 
        add constraint ord_sub_item_ord_item_fk 
        foreign key (order_item_id) 
        references fo_order_item;

    alter table fo_person 
        add constraint person_contact_fk 
        foreign key (contact_id) 
        references fo_contact;

    alter table fo_person 
        add constraint person_location_fk 
        foreign key (location_id) 
        references fo_location;

    alter table fo_person_group 
        add constraint person_group_group_fk 
        foreign key (group) 
        references fo_group;

    alter table fo_person_group 
        add constraint person_group_person_fk 
        foreign key (person) 
        references fo_person;

    alter table fo_phone 
        add constraint phone_contact_fk 
        foreign key (contact_id) 
        references fo_contact;

    alter table fo_provider 
        add constraint provider_address_fk 
        foreign key (address_id) 
        references fo_address;

    alter table fo_provider 
        add constraint provider_contact_fk 
        foreign key (contact_id) 
        references fo_contact;

    alter table fo_vacation 
        add constraint vacation_provider_fk 
        foreign key (provider_id) 
        references fo_provider;

    alter table fo_web_url 
        add constraint web_url_contact_fk 
        foreign key (contact_id) 
        references fo_contact;

    alter table fo_working_hour 
        add constraint wrk_hour_provider_fk 
        foreign key (provider_id) 
        references fo_provider;

    create sequence seq_fo_add_cost_id start 1 increment 1;

    create sequence seq_fo_address_id start 1 increment 1;

    create sequence seq_fo_contact_id start 1 increment 1;

    create sequence seq_fo_dish_comp_id start 1 increment 1;

    create sequence seq_fo_dish_elem_id start 1 increment 1;

    create sequence seq_fo_dish_genere_id start 1 increment 1;

    create sequence seq_fo_dish_id start 1 increment 1;

    create sequence seq_fo_email_id start 1 increment 1;

    create sequence seq_fo_food_ord_grp_id start 1 increment 1;

    create sequence seq_fo_food_ord_id start 1 increment 1;

    create sequence seq_fo_genere_id start 1 increment 1;

    create sequence seq_fo_group_id start 1 increment 1;

    create sequence seq_fo_localization_id start 1 increment 1;

    create sequence seq_fo_ord_item_id start 1 increment 1;

    create sequence seq_fo_organization_id start 1 increment 1;

    create sequence seq_fo_person_group_id start 1 increment 1;

    create sequence seq_fo_person_id start 1 increment 1;

    create sequence seq_fo_phone_id start 1 increment 1;

    create sequence seq_fo_provider_id start 100 increment 1;

    create sequence seq_fo_sub_ord_item_id start 1 increment 1;

    create sequence seq_fo_vacation_id start 1 increment 1;

    create sequence seq_fo_web_url_id start 1 increment 1;

    create sequence seq_fo_working_hour_id start 1 increment 1;
