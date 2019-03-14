-- absen_offer
create index offer_customerName on absen_offer(customer_name);
create index offer_createTime on absen_offer(create_time);
create index offer_series on absen_offer(series);
create index offer_num on absen_offer(num);

-- absen_offer_basic
create index offerBasic_offer on absen_offer_basic(offer);
create index offerBasic_area on absen_offer_basic(area);

-- absen_offer_panels
create index offerPanels_offer on absen_offer_panels(offer);
create index offerPanels_panel on absen_offer_panels(panel);

-- absen_offer_preference
create index offerPreference_offer on absen_offer_preference(offer);
create index offerPreference_name on absen_offer_preference(name);

-- absen_offer_prices
create index offerPrices_offer on absen_offer_prices(offer);

-- absen_offer_service
create index offerService_offer on absen_offer_service(offer);

-- absen_offer_spare_selfdefine
create index offerSpareSelfdefine_offers on absen_offer_spare_selfdefine(offers);
create index offerSpareSelfdefine_panel on absen_offer_spare_selfdefine(panel);

-- absen_offer_spares
create index offerSpares_offer on absen_offer_spares(offer);
create index offerSpares_panel on absen_offer_spares(panel);
create index offerSpares_spare on absen_offer_spares(spare);

-- absen_offer_transfer
create index offerTransfer_orders on absen_offer_transfer(orders);

-- absen_file
create index file_name on absen_file(name);
create index file_UploadTime on absen_file(upload_time);

-- absen_file_package
create index filePackage_name on absen_file_package(name);
create index filePackage_parent on absen_file_package(parent);

-- absen_file_package_relation
create index filePackageRelation_packages on absen_file_package_relation(packages);
create index filePackageRelation_files on absen_file_package_relation(files);

-- absen_file_series
create index fileSeries_series on absen_file_series(series);
create index fileSeries_files on absen_file_series(files);

-- absen_org_user
create index orgUser_name on absen_org_user(name);
create index orgUser_workNo on absen_org_user(work_no);
create index orgUser_area on absen_org_user(area);
create index orgUser_loginName on absen_org_user(login_name);

-- absen_org_user_lang
create index orgUserLang_user on absen_org_user_lang([user]);

-- absen_org_user_resource
create index orgUserResource_users on absen_org_user_resource(users);
create index orgUserResource_resource on absen_org_user_resource(resource);

-- absen_price_assign
create index priceAssign_area on absen_price_assign(area);
create index priceAssign_assigner on absen_price_assign(assigner);

-- absen_prices_details
create index pricesDetails_panel on absen_prices_details(panel);

-- absen_product
create index product_partNo on absen_product(part_no);
create index product_state on absen_product(state);
create index product_series on absen_product(series);
create index product_createTime on absen_product(create_time);
create index product_box on absen_product(box);

-- absen_product_box
create index productBox_scnNo on absen_product_box(scn_no);
create index productBox_modual on absen_product_box(modual);
create index productBox_createTime on absen_product_box(create_time);

-- absen_product_box_lang
create index productBoxLang_box on absen_product_box_lang(box);

-- absen_product_lang
create index productLang_product on absen_product_lang(product);

-- absen_product_modual
create index prodcutModual_scnNo on absen_product_modual(scn_no);
create index productModual_createTime on absen_product_modual(create_time);

-- absen_product_modual_lang
create index productModualLang_modual on absen_product_modual_lang(modual);

-- absen_product_params
create index productParams_product on absen_product_params(product);

-- absen_product_params_lang
create index productParamsLang_params on absen_product_params_lang(params);

-- absen_product_price
create index productPrice_product on absen_product_price(product);
create index productPrice_area on absen_product_price(area);

-- absen_product_sales
create index productSales_product on absen_product_sales(product);

-- absen_product_series
create index productSeries_name on absen_product_series(name);
create index productSeries_parent on absen_product_series(parent);

-- absen_product_series_lang
create index productSeriesLang_series on absen_product_series_lang(series);

-- absen_product_series_standard
create index productSeriesStandard_spare on absen_product_series_standard(spare);
create index productSeriesStandard_series on absen_product_series_standard(series);

-- absen_product_spare
create index productSpare_material on absen_product_spare(material);
create index productSpare_type on absen_product_spare(type);

-- absen_product_spare_lang
create index productSpareLang_spare on absen_product_spare_lang(spare);

-- absen_product_spare_price
create index productSparePrice_spare on absen_product_spare_price(spare);

-- absen_product_standard
create index productStandard_product on absen_product_standard(product);
create index productStandard_spare on absen_product_standard(spare);

-- absen_sales_customer
create index salesCustomer_name on absen_sales_customer(name);

-- absen_sales_customer_user
create index salesCustomerUser_customer on absen_sales_customer_user(customer);
create index salesCustomerUser_users on absen_sales_customer_user(users);

-- absen_series_images
create index seriesImages_series on absen_series_images(series);
create index seriesImages_attachment on absen_series_images(attachment);

-- absen_sys_language_translate
create index sysLanguageTranslate_category on absen_sys_language_translate(category);
create index sysLanguageTranslate_chinese on absen_sys_language_translate(chinese);

-- absen_transport_package
create index transportPackage_transfer on absen_transport_package(transfer);
create index transportPackage_offer on absen_transport_package(offer);


