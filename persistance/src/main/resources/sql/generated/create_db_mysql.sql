
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

    create table fo_add_cost (
        id bigint not null,
        cost decimal(19,2),
        kind varchar(15) not null,
        last_modification_id bigint,
        status varchar(15) not null,
        provider_id bigint,
        primary key (id),
        check (status IN('ACTIVE','INACTIVE','DELETED') and kind IN('MINVAL','DELIVERY','PACK'))
    );

    create table fo_address (
        id bigint not null,
        address1 varchar(255),
        address2 varchar(255),
        city varchar(255),
        comment_text longtext,
        country varchar(255),
        display_index integer not null,
        last_modification_id bigint,
        state varchar(40),
        status varchar(15) not null,
        zip_code varchar(15),
        primary key (id),
        check (status IN('ACTIVE','INACTIVE','DELETED'))
    );

    create table fo_contact (
        id bigint not null,
        last_modification_id bigint,
        status varchar(15) not null,
        primary key (id),
        check (status IN('ACTIVE','INACTIVE','DELETED'))
    );

    create table fo_dish (
        id bigint not null,
        end_day datetime not null,
        start_day datetime not null,
        description varchar(255),
        last_modification_id bigint,
        name varchar(255),
        price decimal(19,2),
        status varchar(15) not null,
        provider_id bigint,
        primary key (id),
        check (status IN('ACTIVE','INACTIVE','DELETED'))
    );

    create table fo_dish_comp (
        id bigint not null,
        description varchar(255),
        last_modification_id bigint,
        max_elements integer not null,
        elements_required integer not null,
        price decimal(19,2),
        status varchar(15) not null,
        use_as_dish_price varchar(1) not null,
        dish_id bigint,
        primary key (id),
        check (status IN('ACTIVE','INACTIVE','DELETED') and use_as_dish_price IN('Y','N'))
    );

    create table fo_dish_elem (
        id bigint not null,
        description varchar(255),
        last_modification_id bigint,
        name varchar(255),
        price decimal(19,2),
        status varchar(15) not null,
        dish_comp_id bigint,
        primary key (id),
        check (status IN('ACTIVE','INACTIVE','DELETED'))
    );

    create table fo_dish_genere (
        id bigint not null,
        last_modification_id bigint,
        dish_id bigint,
        genere_id bigint,
        primary key (id)
    );

    create table fo_email (
        id bigint not null,
        comment_text longtext,
        display_index integer not null,
        email_address varchar(255) not null,
        last_modification_id bigint,
        status varchar(15) not null,
        contact_id bigint,
        primary key (id),
        check (status IN('ACTIVE','INACTIVE','DELETED'))
    );

    create table fo_food_order (
        id bigint not null,
        last_modification_id bigint,
        add_time datetime not null,
        order_status varchar(15) not null,
        order_time datetime not null,
        person_id bigint,
        provider_id bigint,
        primary key (id),
        check (orderStatus IN('ACTIVE','CANCELLED','DELIVERED','CLOSED'))
    );

    create table fo_food_order_group (
        id bigint not null,
        last_modification_id bigint,
        foodOrder bigint,
        group bigint,
        primary key (id)
    );

    create table fo_genere (
        id bigint not null,
        last_modification_id bigint,
        name varchar(255),
        primary key (id)
    );

    create table fo_group (
        id bigint not null,
        last_modification_id bigint,
        name varchar(255) not null,
        organization_owner bigint,
        person_owner bigint,
        primary key (id)
    );

    create table fo_location (
        id bigint not null,
        last_modification_id bigint,
        name varchar(255) not null,
        organization_id bigint,
        primary key (id)
    );

    create table fo_order_item (
        id bigint not null,
        count integer not null,
        last_modification_id bigint,
        price decimal(19,2),
        dish_id bigint,
        food_order_id bigint,
        person_id bigint,
        primary key (id)
    );

    create table fo_order_sub_item (
        id bigint not null,
        last_modification_id bigint,
        dish_comp_id bigint,
        dish_elem_id bigint,
        order_item_id bigint,
        primary key (id)
    );

    create table fo_organization (
        id bigint not null,
        last_modification_id bigint,
        name varchar(255) not null,
        primary key (id)
    );

    create table fo_person (
        id bigint not null,
        desk varchar(15),
        last_modification_id bigint,
        login varchar(255) not null,
        password varchar(255) not null,
        room varchar(15),
        contact_id bigint,
        location_id bigint,
        primary key (id)
    );

    create table fo_person_group (
        id bigint not null,
        last_modification_id bigint,
        group bigint,
        person bigint,
        primary key (id)
    );

    create table fo_phone (
        id bigint not null,
        comment_text longtext,
        display_index integer not null,
        last_modification_id bigint,
        number_type varchar(15) not null,
        phone varchar(15),
        phone_ext varchar(15),
        status varchar(15) not null,
        contact_id bigint,
        primary key (id),
        check (number_type IN('MOBILE','FAX','LANDLINE') and status IN('ACTIVE','INACTIVE','DELETED'))
    );

    create table fo_provider (
        id bigint not null,
        comment varchar(255) not null,
        last_modification_id bigint,
        name varchar(255) not null,
        status varchar(15) not null,
        address_id bigint,
        contact_id bigint,
        primary key (id),
        check (status IN('ACTIVE','INACTIVE','DELETED'))
    );

    create table fo_vacation (
        owner_type varchar(15) not null,
        id bigint not null,
        end_day datetime not null,
        last_modification_id bigint,
        start_day datetime not null,
        provider_id bigint,
        primary key (id)
    );

    create table fo_web_url (
        id bigint not null,
        comment_text longtext,
        display_index integer not null,
        last_modification_id bigint,
        status varchar(15) not null,
        web_url_address varchar(255) not null,
        contact_id bigint,
        primary key (id),
        check (status IN('ACTIVE','INACTIVE','DELETED'))
    );

    create table fo_working_hour (
        owner_type varchar(15) not null,
        id bigint not null,
        end_time datetime not null,
        last_modification_id bigint,
        start_time datetime not null,
        week_day varchar(15) not null,
        provider_id bigint,
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
        references fo_provider (id);

    alter table fo_dish 
        add constraint dish_provider_fk 
        foreign key (provider_id) 
        references fo_provider (id);

    alter table fo_dish_comp 
        add constraint dish_comp_dish_fk 
        foreign key (dish_id) 
        references fo_dish (id);

    alter table fo_dish_elem 
        add constraint dish_elem_dish_comp_fk 
        foreign key (dish_comp_id) 
        references fo_dish_comp (id);

    alter table fo_dish_genere 
        add constraint dish_gene_dish_fk 
        foreign key (dish_id) 
        references fo_dish (id);

    alter table fo_dish_genere 
        add constraint dish_gene_gene_fk 
        foreign key (genere_id) 
        references fo_genere (id);

    alter table fo_email 
        add constraint email_contact_fk 
        foreign key (contact_id) 
        references fo_contact (id);

    alter table fo_food_order 
        add constraint food_order_person_fk 
        foreign key (person_id) 
        references fo_person (id);

    alter table fo_food_order 
        add constraint food_order_provider_fk 
        foreign key (provider_id) 
        references fo_provider (id);

    alter table fo_food_order_group 
        add constraint foodordgrp_foodord_fk 
        foreign key (foodOrder) 
        references fo_food_order (id);

    alter table fo_food_order_group 
        add constraint foodordgrp_group_fk 
        foreign key (group) 
        references fo_group (id);

    alter table fo_group 
        add constraint group_organization_fk 
        foreign key (organization_owner) 
        references fo_organization (id);

    alter table fo_group 
        add constraint group_person_fk 
        foreign key (person_owner) 
        references fo_person (id);

    alter table fo_location 
        add constraint location_organization_fk 
        foreign key (organization_id) 
        references fo_organization (id);

    alter table fo_order_item 
        add constraint order_item_dish_fk 
        foreign key (dish_id) 
        references fo_dish (id);

    alter table fo_order_item 
        add constraint order_item_food_ord_fk 
        foreign key (food_order_id) 
        references fo_food_order (id);

    alter table fo_order_item 
        add constraint order_item_person_fk 
        foreign key (person_id) 
        references fo_person (id);

    alter table fo_order_sub_item 
        add constraint ord_sub_item_dish_comp_fk 
        foreign key (dish_comp_id) 
        references fo_dish_comp (id);

    alter table fo_order_sub_item 
        add constraint ord_sub_item_dish_elem_fk 
        foreign key (dish_elem_id) 
        references fo_dish_elem (id);

    alter table fo_order_sub_item 
        add constraint ord_sub_item_ord_item_fk 
        foreign key (order_item_id) 
        references fo_order_item (id);

    alter table fo_person 
        add constraint person_contact_fk 
        foreign key (contact_id) 
        references fo_contact (id);

    alter table fo_person 
        add constraint person_location_fk 
        foreign key (location_id) 
        references fo_location (id);

    alter table fo_person_group 
        add constraint person_group_group_fk 
        foreign key (group) 
        references fo_group (id);

    alter table fo_person_group 
        add constraint person_group_person_fk 
        foreign key (person) 
        references fo_person (id);

    alter table fo_phone 
        add constraint phone_contact_fk 
        foreign key (contact_id) 
        references fo_contact (id);

    alter table fo_provider 
        add constraint provider_address_fk 
        foreign key (address_id) 
        references fo_address (id);

    alter table fo_provider 
        add constraint provider_contact_fk 
        foreign key (contact_id) 
        references fo_contact (id);

    alter table fo_vacation 
        add constraint vacation_provider_fk 
        foreign key (provider_id) 
        references fo_provider (id);

    alter table fo_web_url 
        add constraint web_url_contact_fk 
        foreign key (contact_id) 
        references fo_contact (id);

    alter table fo_working_hour 
        add constraint wrk_hour_provider_fk 
        foreign key (provider_id) 
        references fo_provider (id);

    create table seq_fo_add_cost_id (
         next_val bigint 
    );

    insert into seq_fo_add_cost_id values ( 1 );

    create table seq_fo_address_id (
         next_val bigint 
    );

    insert into seq_fo_address_id values ( 1 );

    create table seq_fo_contact_id (
         next_val bigint 
    );

    insert into seq_fo_contact_id values ( 1 );

    create table seq_fo_dish_comp_id (
         next_val bigint 
    );

    insert into seq_fo_dish_comp_id values ( 1 );

    create table seq_fo_dish_elem_id (
         next_val bigint 
    );

    insert into seq_fo_dish_elem_id values ( 1 );

    create table seq_fo_dish_genere_id (
         next_val bigint 
    );

    insert into seq_fo_dish_genere_id values ( 1 );

    create table seq_fo_dish_id (
         next_val bigint 
    );

    insert into seq_fo_dish_id values ( 1 );

    create table seq_fo_email_id (
         next_val bigint 
    );

    insert into seq_fo_email_id values ( 1 );

    create table seq_fo_food_ord_grp_id (
         next_val bigint 
    );

    insert into seq_fo_food_ord_grp_id values ( 1 );

    create table seq_fo_food_ord_id (
         next_val bigint 
    );

    insert into seq_fo_food_ord_id values ( 1 );

    create table seq_fo_genere_id (
         next_val bigint 
    );

    insert into seq_fo_genere_id values ( 1 );

    create table seq_fo_group_id (
         next_val bigint 
    );

    insert into seq_fo_group_id values ( 1 );

    create table seq_fo_localization_id (
         next_val bigint 
    );

    insert into seq_fo_localization_id values ( 1 );

    create table seq_fo_ord_item_id (
         next_val bigint 
    );

    insert into seq_fo_ord_item_id values ( 1 );

    create table seq_fo_organization_id (
         next_val bigint 
    );

    insert into seq_fo_organization_id values ( 1 );

    create table seq_fo_person_group_id (
         next_val bigint 
    );

    insert into seq_fo_person_group_id values ( 1 );

    create table seq_fo_person_id (
         next_val bigint 
    );

    insert into seq_fo_person_id values ( 1 );

    create table seq_fo_phone_id (
         next_val bigint 
    );

    insert into seq_fo_phone_id values ( 1 );

    create table seq_fo_provider_id (
         next_val bigint 
    );

    insert into seq_fo_provider_id values ( 100 );

    create table seq_fo_sub_ord_item_id (
         next_val bigint 
    );

    insert into seq_fo_sub_ord_item_id values ( 1 );

    create table seq_fo_vacation_id (
         next_val bigint 
    );

    insert into seq_fo_vacation_id values ( 1 );

    create table seq_fo_web_url_id (
         next_val bigint 
    );

    insert into seq_fo_web_url_id values ( 1 );

    create table seq_fo_working_hour_id (
         next_val bigint 
    );

    insert into seq_fo_working_hour_id values ( 1 );
