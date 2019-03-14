CREATE TABLE [dbo].[absen_basic_dict] (
[id] int NOT NULL identity(1,1) ,
[category] varchar(50) NULL ,
[code] varchar(50) NULL ,
[name] varchar(200) NULL ,
[parent] int NULL
);

CREATE TABLE [dbo].[absen_basic_dict_lang] (
[id] int NOT NULL identity(1,1) ,
[dict] int NOT NULL ,
[lang] varchar(5) NOT NULL ,
[name_lang] varchar(200) NULL
);
CREATE TABLE [dbo].[absen_file] (
[id] bigint NOT NULL ,
[name] varchar(20) NOT NULL ,
[extend] varchar(10) NOT NULL ,
[type] smallint NULL ,
[url] varchar(500) NOT NULL ,
[size] bigint NOT NULL ,
[path] varchar(500) NULL ,
[upload_time] datetime NULL ,
[uploader] int NULL ,
[package] char(10) NULL
);
CREATE TABLE [dbo].[absen_file_connect_label] (
[id] int NOT NULL identity(1,1) ,
[label] int NOT NULL ,
[file] bigint NOT NULL
);
CREATE TABLE [dbo].[absen_file_labels] (
[id] int NOT NULL IDENTITY(1,1) ,
[name] varchar(50) NOT NULL ,
[code] varchar(50) NOT NULL ,
[creater] int NULL ,
[create_time] datetime NULL ,
[status] smallint NOT NULL ,
[parent] int NULL
);
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description',
'SCHEMA', N'dbo',
'TABLE', N'absen_file_labels',
'COLUMN', N'status')) > 0)
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'1. 有效；2失效'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'absen_file_labels'
, @level2type = 'COLUMN', @level2name = N'status'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'1. 有效；2失效'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'absen_file_labels'
, @level2type = 'COLUMN', @level2name = N'status';


CREATE TABLE [dbo].[absen_file_package] (
[id] int NOT NULL identity(1,1) ,
[name] varchar(50) NOT NULL ,
[parent] int NOT NULL ,
[icon] varchar(50) NOT NULL ,
[create_time] datetime NULL ,
[update_time] datetime NULL ,
[creater] int NULL ,
[updater] int NULL ,
[full_path] varchar(200) NOT NULL
);
CREATE TABLE [dbo].[absen_file_package_security_role] (
[id] int NOT NULL identity(1,1) ,
[package] int NOT NULL ,
[role] int NOT NULL ,
[security] smallint NOT NULL
);
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description',
'SCHEMA', N'dbo',
'TABLE', N'absen_file_package_security_role',
'COLUMN', N'security')) > 0)
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'1.可打包下载；2可查看；3可分享；4可删除；5可重命名；6可添加文件；7可添加文件夹'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'absen_file_package_security_role'
, @level2type = 'COLUMN', @level2name = N'security'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'1.可打包下载；2可查看；3可分享；4可删除；5可重命名；6可添加文件；7可添加文件夹'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'absen_file_package_security_role'
, @level2type = 'COLUMN', @level2name = N'security';
CREATE TABLE [dbo].[absen_file_package_security_user] (
[id] bigint NOT NULL IDENTITY(1,1) ,
[package] int NOT NULL ,
[user] int NOT NULL ,
[security] smallint NOT NULL
);
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description',
'SCHEMA', N'dbo',
'TABLE', N'absen_file_package_security_user',
'COLUMN', N'security')) > 0)
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'1.可打包下载；2可查看；3可分享；4可删除；5可重命名；6可添加文件；7可添加文件夹'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'absen_file_package_security_user'
, @level2type = 'COLUMN', @level2name = N'security'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'1.可打包下载；2可查看；3可分享；4可删除；5可重命名；6可添加文件；7可添加文件夹'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'absen_file_package_security_user'
, @level2type = 'COLUMN', @level2name = N'security';
CREATE TABLE [dbo].[absen_file_security_role] (
[id] int NOT NULL identity(1,1) ,
[file] bigint NOT NULL ,
[role] int NOT NULL ,
[security] smallint NOT NULL
);
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description',
'SCHEMA', N'dbo',
'TABLE', N'absen_file_security_role',
'COLUMN', N'security')) > 0)
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'1.可下载；2可查看；3可分享；4可删除；5可重命名'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'absen_file_security_role'
, @level2type = 'COLUMN', @level2name = N'security'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'1.可下载；2可查看；3可分享；4可删除；5可重命名'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'absen_file_security_role'
, @level2type = 'COLUMN', @level2name = N'security'
;
CREATE TABLE [dbo].[absen_file_security_temp] (
[id] int NOT NULL IDENTITY(1,1) ,
[file] int NOT NULL ,
[user] int NOT NULL ,
[type] smallint NOT NULL ,
[start_time] datetime NULL ,
[end_time] datetime NULL ,
[counts_total] int NULL ,
[counts_operated] int NULL
);
CREATE TABLE [dbo].[absen_file_security_user] (
[id] int NOT NULL identity(1,1) ,
[user] int NOT NULL ,
[file] bigint NOT NULL ,
[security] smallint NOT NULL
);
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description',
'SCHEMA', N'dbo',
'TABLE', N'absen_file_security_user',
'COLUMN', N'security')) > 0)
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'1.可下载；2可查看；3可分享；4可删除；5可重命名'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'absen_file_security_user'
, @level2type = 'COLUMN', @level2name = N'security'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'1.可下载；2可查看；3可分享；4可删除；5可重命名'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'absen_file_security_user'
, @level2type = 'COLUMN', @level2name = N'security';

CREATE TABLE [dbo].[absen_log_product] (
[id] int NOT NULL identity(1,1) ,
[product] int NOT NULL ,
[updater] int NOT NULL ,
[update_time] datetime NULL ,
[type] smallint NOT NULL ,
[version] varchar(18) NULL ,
[area] int NULL
);
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description',
'SCHEMA', N'dbo',
'TABLE', N'absen_log_product',
'COLUMN', N'type')) > 0)
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'1. 新增；2修改；3删除；4变更状态'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'absen_log_product'
, @level2type = 'COLUMN', @level2name = N'type'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'1. 新增；2修改；3删除；4变更状态'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'absen_log_product'
, @level2type = 'COLUMN', @level2name = N'type';
CREATE TABLE [dbo].[absen_log_product_detail] (
[id] int NOT NULL identity(1,1) ,
[log_id] int NOT NULL ,
[field] varchar(30) NOT NULL ,
[value_origin] varchar(200) NULL ,
[value_new] varchar(200) NOT NULL
);
CREATE TABLE [dbo].[absen_log_product_price] (
[id] int NOT NULL identity(1,1) ,
[product] int NOT NULL ,
[price_origin] decimal(18) NULL ,
[price_new] decimal(18) NOT NULL ,
[updater] int NOT NULL ,
[update_time] datetime NOT NULL ,
[version] varchar(18) NULL ,
[area] int NULL
);
CREATE TABLE [dbo].[absen_log_spare] (
[id] int NOT NULL identity(1,1) ,
[spare] int NOT NULL ,
[updater] int NOT NULL ,
[update_time] datetime NULL ,
[type] smallint NOT NULL ,
[version] varchar(18) NULL ,
[area] int NULL
);
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description',
'SCHEMA', N'dbo',
'TABLE', N'absen_log_spare',
'COLUMN', N'type')) > 0)
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'1. 新增；2修改；3删除；4变更状态'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'absen_log_spare'
, @level2type = 'COLUMN', @level2name = N'type'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'1. 新增；2修改；3删除；4变更状态'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'absen_log_spare'
, @level2type = 'COLUMN', @level2name = N'type';
CREATE TABLE [dbo].[absen_log_spare_detail] (
[id] int NOT NULL identity(1,1) ,
[log_id] int NOT NULL ,
[field] varchar(30) NOT NULL ,
[value_origin] varchar(200) NULL ,
[value_new] varchar(200) NOT NULL
);
CREATE TABLE [dbo].[absen_log_spare_price] (
[id] int NOT NULL identity(1,1) ,
[spare] int NOT NULL ,
[price_origin] decimal(18) NULL ,
[price_new] decimal(18) NOT NULL ,
[updater] int NOT NULL ,
[update_time] datetime NOT NULL ,
[version] varchar(18) NULL ,
[area] int NULL
);
CREATE TABLE [dbo].[absen_offer] (
[id] int NOT NULL IDENTITY(1,1) ,
[num] varchar(20) NOT NULL ,
[create_time] datetime NULL ,
[creater] int NOT NULL ,
[customer] int NULL ,
[customer_name] varchar(500) NOT NULL ,
[shipping] varchar(500) NULL ,
[cost] decimal(18) NULL ,
[unit] varchar(20) NULL ,
[version] varchar(18) NOT NULL ,
[parent] int NULL ,
[payment] varchar(200) NULL ,
[guarantee] smallint NOT NULL ,
[discount] decimal(18) NULL ,
[price_real] decimal(18) NULL ,
[remark] varchar(5000) NULL ,
[description] varchar(5000) NULL ,
[trader] varchar(200) NULL ,
[status] smallint NULL ,
[valid] smallint NULL ,
[clause] varchar(200) NULL ,
[waiting_date] smallint NULL
);
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description',
'SCHEMA', N'dbo',
'TABLE', N'absen_offer',
'COLUMN', N'trader')) > 0)
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'字典的名称值'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'absen_offer'
, @level2type = 'COLUMN', @level2name = N'trader'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'字典的名称值'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'absen_offer'
, @level2type = 'COLUMN', @level2name = N'trader'
;
CREATE TABLE [dbo].[absen_offer_free] (
[id] int NOT NULL identity(1,1) ,
[spare] int NULL ,
[spare_name] varchar(200) NOT NULL ,
[brand] varchar(200) NULL ,
[count_guid] int NULL ,
[count_real] int NOT NULL ,
[price_cost] decimal(18) NULL ,
[unit] decimal(18) NULL ,
[offer] int NOT NULL ,
[product] int NOT NULL
);
CREATE TABLE [dbo].[absen_offer_product] (
[id] int NOT NULL identity(1,1) ,
[offer] int NOT NULL ,
[product] int NOT NULL ,
[transverse] int NOT NULL ,
[portrait] int NOT NULL ,
[width] decimal(18) NOT NULL ,
[height] decimal(18) NOT NULL ,
[price_cost] decimal(18) NOT NULL ,
[price_sale] decimal(18) NOT NULL ,
[price] decimal(18) NOT NULL ,
[unit] varchar(5) NOT NULL ,
[count] int NULL
);
CREATE TABLE [dbo].[absen_offer_service] (
[id] int NOT NULL identity(1,1) ,
[offer] int NOT NULL ,
[name] varchar(200) NOT NULL ,
[counts] int NOT NULL ,
[price] decimal(18) NOT NULL ,
[unit] varchar(5) NULL
);
CREATE TABLE [dbo].[absen_offer_spare] (
[id] int NOT NULL identity(1,1) ,
[spare] int NULL ,
[spare_name] varchar(200) NOT NULL ,
[brand] varchar(200) NULL ,
[count_guid] int NULL ,
[count_real] int NOT NULL ,
[price_cost] decimal(18) NOT NULL ,
[price] decimal(18) NOT NULL ,
[unit] varchar(5) NOT NULL ,
[offer] int NOT NULL ,
[product] int NOT NULL
);
CREATE TABLE [dbo].[absen_offer_standard] (
[id] int NOT NULL identity(1,1) ,
[spare] int NULL ,
[spare_name] varchar(200) NOT NULL ,
[brand] varchar(200) NULL ,
[count_guid] int NULL ,
[count_real] int NOT NULL ,
[price_cost] decimal(18) NULL ,
[unit] decimal(18) NULL ,
[offer] int NOT NULL ,
[cost_price] decimal(18) NULL ,
[product] int NOT NULL
);
CREATE TABLE [dbo].[absen_org_department] (
[id] int NOT NULL IDENTITY(1,1) ,
[code] varchar(50) NOT NULL ,
[name] varchar(200) NOT NULL ,
[parent] int NULL ,
[leader] int NULL ,
[assistanter] int NULL ,
[creater] int NULL ,
[create_time] datetime NULL,
[level] smallint not null,
status smallint not null
);
CREATE TABLE [dbo].[absen_org_department_lang] (
[id] int NOT NULL identity(1,1) ,
[department] int NOT NULL ,
[lang] varchar(5) NOT NULL ,
[name_lang] varchar(200) NULL
);
CREATE TABLE [dbo].[absen_org_department_user] (
[id] int NOT NULL identity(1,1) ,
[department] int NOT NULL ,
[user] int NOT NULL
);
CREATE TABLE [dbo].[absen_org_resource] (
[id] int NOT NULL IDENTITY(1,1) ,
[code] varchar(50) NOT NULL ,
[name] varchar(200) NULL ,
[parent] int NULL ,
[type] int NOT NULL ,
[creater] int NULL ,
[create_time] datetime NULL
);
CREATE TABLE [dbo].[absen_org_resource_lang] (
[id] int NOT NULL identity(1,1) ,
[resource] int NOT NULL ,
[language] varchar(5) NOT NULL ,
[name_lang] varchar(200) NULL
);
CREATE TABLE [dbo].[absen_org_role] (
[id] int NOT NULL IDENTITY(1,1) ,
[name] varchar(200) NOT NULL ,
[role] varchar(20) NOT NULL ,
[status] int NOT NULL ,
[creater] int NULL ,
[create_time] datetime NULL
);
CREATE TABLE [dbo].[absen_org_role_lang] (
[id] int NOT NULL identity(1,1) ,
[role] int NOT NULL ,
[language] varchar(5) NOT NULL ,
[name_lang] varchar(200) NULL
);
CREATE TABLE [dbo].[absen_org_role_resource] (
[id] int NOT NULL identity(1,1) ,
[role] int NOT NULL ,
[resource] int NOT NULL
);
CREATE TABLE [dbo].[absen_org_role_user] (
[id] int NOT NULL identity(1,1) ,
[role] int NOT NULL ,
[user] int NOT NULL
);
CREATE TABLE [dbo].[absen_org_user] (
[id] int NOT NULL IDENTITY(1,1) ,
[name] varchar(50) NULL ,
[work_no] varchar(20) NULL ,
[login_name] varchar(200) NOT NULL ,
[password] varchar(64) NOT NULL ,
[salt] varchar(30) NOT NULL ,
[language] varchar(200) NULL ,
[language_default] varchar(5) NOT NULL ,
[area] int NULL ,
[avatar] varchar(1000) NULL ,
[creater] int NULL ,
[create_time] datetime NULL ,
[status] smallint NULL ,
[delete_flag] smallint NULL ,
[phone] varchar(18) NULL ,
[email] varchar(255) NULL ,
[company] varchar(255) NULL
);


SET IDENTITY_INSERT [dbo].[absen_org_user] ON;
INSERT INTO [dbo].[absen_org_user] ([id], [name], [work_no], [login_name], [password], [salt], [language], [language_default], [area], [avatar], [creater], [create_time], [status], [delete_flag], [phone], [email], [company]) VALUES (N'1', N'管理员', N'10000', N'admin', N'2c3c6f2348783a578225df3bcbd489fc6a0c508b', N'1ec6129fdee90a83', N'zh,en', N'zh', N'0', N'1', N'1', N'2017-10-10 14:59:39.000', N'1', N'0', null, null, null);
SET IDENTITY_INSERT [dbo].[absen_org_user] OFF;


CREATE TABLE [dbo].[absen_org_user_lang] (
[id] int NOT NULL identity(1,1) ,
[user] int NOT NULL ,
[language] varchar(5) NOT NULL ,
[name_lang] varchar(200) NULL ,
[nick_name_lang] varchar(200) NULL
);
CREATE TABLE [dbo].[absen_org_user_resource] (
[id] int NOT NULL identity(1,1) ,
[user] int NOT NULL ,
[resource] int NOT NULL
);
CREATE TABLE [dbo].[absen_product] (
[id] int NOT NULL identity(1,1) ,
[configuration] int NULL ,
[certification] varchar(50) NULL ,
[part_no] varchar(50) NULL ,
[state] varchar(200) NULL ,
[series] int NOT NULL ,
[featured] smallint NULL ,
[update_time] datetime NULL ,
[updater] int NULL ,
[execution_time] datetime NULL ,
[type] varchar(20) NULL ,
[status] varchar(20) NULL ,
[color] varchar(50) NULL ,
[creater] int NULL ,
[create_time] datetime NULL ,
[request] int NULL ,
[ylzd1] varchar(500) NULL ,
[ylzd2] varchar(500) NULL ,
[ylzd3] varchar(500) NULL ,
[ylzd4] varchar(500) NULL ,
[ylzd5] varchar(500) NULL
)
;
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description',
'SCHEMA', N'dbo',
'TABLE', N'absen_product',
'COLUMN', N'type')) > 0)
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'字典'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'absen_product'
, @level2type = 'COLUMN', @level2name = N'type'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'字典'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'absen_product'
, @level2type = 'COLUMN', @level2name = N'type'
;
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description',
'SCHEMA', N'dbo',
'TABLE', N'absen_product',
'COLUMN', N'status')) > 0)
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'字典'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'absen_product'
, @level2type = 'COLUMN', @level2name = N'status'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'字典'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'absen_product'
, @level2type = 'COLUMN', @level2name = N'status'
;
CREATE TABLE [dbo].[absen_product_box] (
[id] int NOT NULL IDENTITY(1,1) ,
[scn_no] varchar(20) NULL ,
[modual] varchar(50) NOT NULL ,
[transverse_count] smallint NOT NULL ,
[portrait_count] smallint NOT NULL ,
[weight] decimal(18) NULL ,
[transverse_pix] int NULL ,
[portrait_pix] int NULL ,
[height] decimal(18) NOT NULL ,
[width] decimal(18) NOT NULL ,
[thickness] decimal(18) NOT NULL ,
[box_type] varchar(20) NULL
);
CREATE TABLE [dbo].[absen_product_certification] (
[id] int NOT NULL IDENTITY(1,1) ,
[name] varchar(50) NOT NULL ,
[code] varchar(20) NULL ,
[status] smallint NOT NULL
);

-- ----------------------------
-- Records of absen_product_certification
-- ----------------------------
SET IDENTITY_INSERT [dbo].[absen_product_certification] ON
;
SET IDENTITY_INSERT [dbo].[absen_product_certification] OFF
;


CREATE TABLE [dbo].[absen_product_configuration] (
[id] int NOT NULL IDENTITY(1,1) ,
[transverse] smallint NOT NULL ,
[portrait] smallint NOT NULL ,
[remark] varchar(200) NULL ,
[create_time] datetime NULL ,
[creater] int NULL ,
[special] int NULL
);

-- ----------------------------
-- Records of absen_product_configuration
-- ----------------------------
SET IDENTITY_INSERT [dbo].[absen_product_configuration] ON
;
SET IDENTITY_INSERT [dbo].[absen_product_configuration] OFF
;

CREATE TABLE [dbo].[absen_product_images] (
[id] int NOT NULL identity(1,1) ,
[product] int NOT NULL ,
[default] smallint NOT NULL ,
[url_thum] varchar(500) NOT NULL ,
[sort] smallint NULL ,
[uploader] int NULL ,
[upload_time] datetime NULL ,
[url_original] varchar(500) NOT NULL ,
[attachment] int NULL
)


;

CREATE TABLE [dbo].[absen_product_lang] (
[id] int NOT NULL identity(1,1) ,
[lang] varchar(5) NOT NULL ,
[product_lang] varchar(200) NOT NULL ,
[remark_lang] varchar(200) NULL ,
[color_lang] varchar(200) NULL
)


;

CREATE TABLE [dbo].[absen_product_modual] (
[id] int NOT NULL IDENTITY(1,1) ,
[scn_no] varchar(20) NULL ,
[height] decimal(18) NOT NULL ,
[width] decimal(18) NOT NULL ,
[transverse] int NOT NULL ,
[portrait] int NOT NULL ,
[pitch] decimal(18,2) NOT NULL ,
[density] int NULL ,
[lamp] int NOT NULL ,
[configuration] varchar(50) NULL ,
[weight] decimal(18,2) NULL
);


CREATE TABLE [dbo].[absen_product_params] (
[id] int NOT NULL IDENTITY(1,1) ,
[product] int NOT NULL ,
[box] int NOT NULL ,
[control] varchar(20) NULL ,
[calibration] smallint NULL ,
[intelligent] smallint NULL ,
[rigging] int NULL ,
[stack] smallint NULL ,
[front是] smallint NULL ,
[fix_modual] varchar(50) NULL ,
[fix_psu] varchar(50) NULL ,
[ip_rating] varchar(50) NULL ,
[brightness] varchar(50) NULL ,
[contrast_ratio] varchar(50) NULL ,
[gray_scale] varchar(50) NULL ,
[refresh] varchar(50) NULL ,
[viewing] varchar(50) NULL ,
[power_max] int NULL ,
[power_avg] int NULL ,
[driving_ic] varchar(20) NULL ,
[driving_type] varchar(20) NULL ,
[psu] varchar(200) NULL ,
[psu_power] int NULL ,
[psu_count] smallint NULL ,
[standard_carry_lower] int NULL ,
[standard_carry_high] int NULL ,
[ylzd1] varchar(200) NULL ,
[ylzd2] varchar(200) NULL ,
[ylzd3] varchar(200) NULL ,
[ylzd4] varchar(200) NULL ,
[ylzd5] varchar(200) NULL
)


;
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description',
'SCHEMA', N'dbo',
'TABLE', N'absen_product_params',
'COLUMN', N'intelligent')) > 0)
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'是、否'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'absen_product_params'
, @level2type = 'COLUMN', @level2name = N'intelligent'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'是、否'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'absen_product_params'
, @level2type = 'COLUMN', @level2name = N'intelligent'
;

-- ----------------------------
-- Records of absen_product_params
-- ----------------------------
SET IDENTITY_INSERT [dbo].[absen_product_params] ON
;
SET IDENTITY_INSERT [dbo].[absen_product_params] OFF
;

CREATE TABLE [dbo].[absen_product_params_lang] (
[id] int NOT NULL identity(1,1) ,
[parmas] int NOT NULL ,
[lang] varchar(5) NOT NULL ,
[control_lang] varchar(200) NULL ,
[fix_moduall_lang] varchar(200) NULL ,
[fix_psu] varchar(200) NULL
)


;

-- ----------------------------
-- Records of absen_product_params_lang
-- ----------------------------

CREATE TABLE [dbo].[absen_product_price] (
[id] int NOT NULL identity(1,1) ,
[product] int NOT NULL ,
[price] decimal(18) NOT NULL ,
[unit] varchar(20) NOT NULL ,
[area] int NOT NULL ,
[sale_price] decimal(18) NULL
)


;

-- ----------------------------
-- Records of absen_product_price
-- ----------------------------

CREATE TABLE [dbo].[absen_product_sales] (
[id] int NOT NULL identity(1,1) ,
[product] int NOT NULL ,
[area] int NOT NULL ,
[status] smallint NOT NULL ,
[discount_type] smallint NULL ,
[unit] varchar(5) NULL ,
[suggest] smallint NULL
)


;
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description',
'SCHEMA', N'dbo',
'TABLE', N'absen_product_sales',
'COLUMN', N'discount_type')) > 0)
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'字典，1代表给指导价进行打折，计算销售价；2代表给成本价和利润率计算销售价'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'absen_product_sales'
, @level2type = 'COLUMN', @level2name = N'discount_type'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'字典，1代表给指导价进行打折，计算销售价；2代表给成本价和利润率计算销售价'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'absen_product_sales'
, @level2type = 'COLUMN', @level2name = N'discount_type'
;

-- ----------------------------
-- Records of absen_product_sales
-- ----------------------------

CREATE TABLE [dbo].[absen_product_series] (
[id] int NOT NULL IDENTITY(1,1) ,
[name] varchar(200) NOT NULL ,
[line] int NOT NULL ,
[status] smallint NOT NULL ,
[creater] int NULL ,
[create_time] datetime NULL ,
[image] varchar(500) NOT NULL ,
[parent] int NULL ,
[remark] varchar(2000) NULL ,
[type] smallint NULL
)


;

-- ----------------------------
-- Records of absen_product_series
-- ----------------------------
SET IDENTITY_INSERT [dbo].[absen_product_series] ON
;
SET IDENTITY_INSERT [dbo].[absen_product_series] OFF
;

CREATE TABLE [dbo].[absen_product_series_lang] (
[id] int NOT NULL identity(1,1) ,
[lang] varchar(5) NOT NULL ,
[series] int NOT NULL ,
[name_lang] varchar(2000) NULL ,
[remark_lang] varchar(2000) NULL
)


;

-- ----------------------------
-- Records of absen_product_series_lang
-- ----------------------------

CREATE TABLE [dbo].[absen_product_series_standard] (
[id] int NOT NULL identity(1,1) ,
[spare] int NOT NULL ,
[series] int NOT NULL ,
[standard] smallint NULL ,
[counts] decimal(18) NULL ,
[type] smallint NOT NULL
)


;
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description',
'SCHEMA', N'dbo',
'TABLE', N'absen_product_series_standard',
'COLUMN', N'type')) > 0)
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'1.标配 2.选配 3.免费'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'absen_product_series_standard'
, @level2type = 'COLUMN', @level2name = N'type'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'1.标配 2.选配 3.免费'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'absen_product_series_standard'
, @level2type = 'COLUMN', @level2name = N'type'
;

-- ----------------------------
-- Records of absen_product_series_standard
-- ----------------------------

CREATE TABLE [dbo].[absen_product_spare] (
[id] int NOT NULL IDENTITY(1,1) ,
[brand] varchar(200) NULL ,
[model] varchar(200) NULL ,
[update_time] datetime NULL ,
[excute_time] datetime NULL ,
[material] varchar(50) NULL ,
[description] varchar(2000) NULL ,
[remark] varchar(2000) NULL ,
[unit] varchar(20) NULL ,
[type] varchar(200) NULL
);

CREATE TABLE [dbo].[absen_product_spare_lang] (
[id] int NOT NULL identity(1,1) ,
[brand_lang] varchar(200) NULL ,
[model_lang] varchar(200) NULL ,
[description_lang] varchar(2000) NULL ,
[remark_lang] int NOT NULL ,
[lang] varchar(5) NOT NULL
)


;

-- ----------------------------
-- Records of absen_product_spare_lang
-- ----------------------------

CREATE TABLE [dbo].[absen_product_spare_price] (
[id] int NOT NULL identity(1,1) ,
[spare] int NOT NULL ,
[price] decimal(18) NOT NULL ,
[unit] varchar(5) NOT NULL ,
[area] int NULL ,
[sale_price] decimal(18) NULL
)


;

-- ----------------------------
-- Records of absen_product_spare_price
-- ----------------------------

CREATE TABLE [dbo].[absen_product_special] (
[id] int NOT NULL IDENTITY(1,1) ,
[fields] varchar(200) NOT NULL ,
[code] varchar(50) NOT NULL
)


;
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description',
'SCHEMA', N'dbo',
'TABLE', N'absen_product_special',
'COLUMN', N'fields')) > 0)
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'高刷、宽电压、低温、换IC、高湿度等'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'absen_product_special'
, @level2type = 'COLUMN', @level2name = N'fields'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'高刷、宽电压、低温、换IC、高湿度等'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'absen_product_special'
, @level2type = 'COLUMN', @level2name = N'fields'
;

-- ----------------------------
-- Records of absen_product_special
-- ----------------------------
SET IDENTITY_INSERT [dbo].[absen_product_special] ON
;
SET IDENTITY_INSERT [dbo].[absen_product_special] OFF
;

CREATE TABLE [dbo].[absen_product_standard] (
[id] int NOT NULL identity(1,1) ,
[product] int NOT NULL ,
[spare] int NOT NULL ,
[standard] smallint NULL ,
[type] smallint NOT NULL
);
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description',
'SCHEMA', N'dbo',
'TABLE', N'absen_product_standard',
'COLUMN', N'type')) > 0)
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'1标配  2选配  3免费'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'absen_product_standard'
, @level2type = 'COLUMN', @level2name = N'type'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'1标配  2选配  3免费'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'absen_product_standard'
, @level2type = 'COLUMN', @level2name = N'type'
;

CREATE TABLE [dbo].[absen_product_standard_year] (
[standard] int NULL ,
[year] smallint NULL ,
[counts] decimal(18) NULL ,
[type] smallint NULL ,
[max] decimal(18) NULL ,
[min] decimal(18) NULL ,
[spel] varchar(200) NULL ,
[unit_type] smallint NULL
);

IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description',
'SCHEMA', N'dbo',
'TABLE', N'absen_product_standard_year',
'COLUMN', N'type')) > 0)
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'1.按照比例计算;2.按照屏幕数量计算;3.约定计算方式;4按照订单数量计算'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'absen_product_standard_year'
, @level2type = 'COLUMN', @level2name = N'type'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'1.按照比例计算;2.按照屏幕数量计算;3.约定计算方式;4按照订单数量计算'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'absen_product_standard_year'
, @level2type = 'COLUMN', @level2name = N'type'
;
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description',
'SCHEMA', N'dbo',
'TABLE', N'absen_product_standard_year',
'COLUMN', N'unit_type')) > 0)
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'1. 面积；2 箱体数量；3 模组类型数量'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'absen_product_standard_year'
, @level2type = 'COLUMN', @level2name = N'unit_type'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'1. 面积；2 箱体数量；3 模组类型数量'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'absen_product_standard_year'
, @level2type = 'COLUMN', @level2name = N'unit_type'
;

CREATE TABLE [dbo].[absen_sales_address] (
[id] int NOT NULL identity(1,1) ,
[level1] varchar(500) NULL ,
[level2] varchar(500) NULL ,
[level3] varchar(500) NULL ,
[level4] varchar(500) NULL ,
[level5] varchar(500) NULL ,
[details] varchar(1000) NULL
)


;
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description',
'SCHEMA', N'dbo',
'TABLE', N'absen_sales_address',
'COLUMN', N'level1')) > 0)
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'国家'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'absen_sales_address'
, @level2type = 'COLUMN', @level2name = N'level1'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'国家'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'absen_sales_address'
, @level2type = 'COLUMN', @level2name = N'level1'
;
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description',
'SCHEMA', N'dbo',
'TABLE', N'absen_sales_address',
'COLUMN', N'level2')) > 0)
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'省、州、大区等划分'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'absen_sales_address'
, @level2type = 'COLUMN', @level2name = N'level2'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'省、州、大区等划分'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'absen_sales_address'
, @level2type = 'COLUMN', @level2name = N'level2'
;
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description',
'SCHEMA', N'dbo',
'TABLE', N'absen_sales_address',
'COLUMN', N'level3')) > 0)
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'市'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'absen_sales_address'
, @level2type = 'COLUMN', @level2name = N'level3'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'市'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'absen_sales_address'
, @level2type = 'COLUMN', @level2name = N'level3'
;
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description',
'SCHEMA', N'dbo',
'TABLE', N'absen_sales_address',
'COLUMN', N'level4')) > 0)
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'县、城区等划分'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'absen_sales_address'
, @level2type = 'COLUMN', @level2name = N'level4'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'县、城区等划分'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'absen_sales_address'
, @level2type = 'COLUMN', @level2name = N'level4'
;
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description',
'SCHEMA', N'dbo',
'TABLE', N'absen_sales_address',
'COLUMN', N'level5')) > 0)
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'街道'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'absen_sales_address'
, @level2type = 'COLUMN', @level2name = N'level5'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'街道'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'absen_sales_address'
, @level2type = 'COLUMN', @level2name = N'level5'
;


CREATE TABLE [dbo].[absen_sales_customer] (
[id] int NOT NULL identity(1,1) ,
[name] varchar(200) NOT NULL ,
[website] varchar(500) NULL ,
[ticker_symbol] varchar(50) NULL ,
[parent] int NULL ,
[employees] int NULL ,
[ownership] varchar(500) NULL ,
[type] varchar(50) NULL ,
[account_type] varchar(200) NULL ,
[account_number] varchar(500) NULL ,
[location] varchar(200) NULL ,
[phone] varchar(50) NULL ,
[fax] varchar(50) NULL ,
[email] varchar(500) NULL ,
[rating] int NULL ,
[revenue] bigint NULL ,
[sic_code] varchar(50) NULL ,
[billing] int NULL ,
[shipping] int NULL ,
[description] varchar(5000) NULL ,
[status] smallint NOT NULL
)


;
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description',
'SCHEMA', N'dbo',
'TABLE', N'absen_sales_customer',
'COLUMN', N'type')) > 0)
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'字典'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'absen_sales_customer'
, @level2type = 'COLUMN', @level2name = N'type'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'字典'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'absen_sales_customer'
, @level2type = 'COLUMN', @level2name = N'type'
;
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description',
'SCHEMA', N'dbo',
'TABLE', N'absen_sales_customer',
'COLUMN', N'account_type')) > 0)
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'字典'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'absen_sales_customer'
, @level2type = 'COLUMN', @level2name = N'account_type'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'字典'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'absen_sales_customer'
, @level2type = 'COLUMN', @level2name = N'account_type'
;


CREATE TABLE [dbo].[absen_sales_customer_user] (
[id] int NOT NULL identity(1,1) ,
[customer] int NOT NULL ,
[user] int NOT NULL ,
[assigner] int NULL ,
[assign_time] datetime NULL
)


;


CREATE TABLE [dbo].[absen_sales_levels] (
[id] int NOT NULL identity(1,1) ,
[code] varchar(50) NOT NULL ,
[name] varchar(50) NOT NULL ,
[org] int NULL ,
[discount] decimal(18) NULL ,
[profit] decimal(18) NULL
)


;


;
CREATE TABLE [dbo].[absen_sales_levels_lang] (
[id] int NOT NULL identity(1,1) ,
[level] int NOT NULL ,
[lang] varchar(5) NOT NULL ,
[name_lang] varchar(200) NULL
);
CREATE TABLE [dbo].[absen_sys_attachment] (
[id] int NOT NULL IDENTITY(1,1) ,
[file_name] varchar(200) NOT NULL ,
[file_type] varchar(10) NOT NULL ,
[upload_time] datetime NULL ,
[uploader] int NULL ,
[file_path] varchar(500) NOT NULL ,
[file_url] varchar(500) NOT NULL ,
[file_size] bigint NULL
);

ALTER TABLE [dbo].[absen_basic_dict] ADD PRIMARY KEY ([id]);

ALTER TABLE [dbo].[absen_basic_dict_lang] ADD PRIMARY KEY ([id]);

ALTER TABLE [dbo].[absen_file] ADD PRIMARY KEY ([id]);


ALTER TABLE [dbo].[absen_file_connect_label] ADD PRIMARY KEY ([id]);

ALTER TABLE [dbo].[absen_file_labels] ADD PRIMARY KEY ([id]);

ALTER TABLE [dbo].[absen_file_package] ADD PRIMARY KEY ([id])
;

-- ----------------------------
-- Indexes structure for table absen_file_package_security_role
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table absen_file_package_security_role
-- ----------------------------
ALTER TABLE [dbo].[absen_file_package_security_role] ADD PRIMARY KEY ([id])
;

-- ----------------------------
-- Indexes structure for table absen_file_package_security_user
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table absen_file_package_security_user
-- ----------------------------
ALTER TABLE [dbo].[absen_file_package_security_user] ADD PRIMARY KEY ([id])
;

-- ----------------------------
-- Indexes structure for table absen_file_security_role
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table absen_file_security_role
-- ----------------------------
ALTER TABLE [dbo].[absen_file_security_role] ADD PRIMARY KEY ([id])
;

-- ----------------------------
-- Indexes structure for table absen_file_security_temp
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table absen_file_security_temp
-- ----------------------------
ALTER TABLE [dbo].[absen_file_security_temp] ADD PRIMARY KEY ([id])
;

-- ----------------------------
-- Indexes structure for table absen_file_security_user
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table absen_file_security_user
-- ----------------------------
ALTER TABLE [dbo].[absen_file_security_user] ADD PRIMARY KEY ([id])
;

-- ----------------------------
-- Indexes structure for table absen_log_product
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table absen_log_product
-- ----------------------------
ALTER TABLE [dbo].[absen_log_product] ADD PRIMARY KEY ([id])
;

-- ----------------------------
-- Indexes structure for table absen_log_product_detail
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table absen_log_product_detail
-- ----------------------------
ALTER TABLE [dbo].[absen_log_product_detail] ADD PRIMARY KEY ([id])
;

-- ----------------------------
-- Indexes structure for table absen_log_product_price
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table absen_log_product_price
-- ----------------------------
ALTER TABLE [dbo].[absen_log_product_price] ADD PRIMARY KEY ([id])
;

-- ----------------------------
-- Indexes structure for table absen_log_spare
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table absen_log_spare
-- ----------------------------
ALTER TABLE [dbo].[absen_log_spare] ADD PRIMARY KEY ([id])
;

-- ----------------------------
-- Indexes structure for table absen_log_spare_detail
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table absen_log_spare_detail
-- ----------------------------
ALTER TABLE [dbo].[absen_log_spare_detail] ADD PRIMARY KEY ([id])
;

-- ----------------------------
-- Indexes structure for table absen_log_spare_price
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table absen_log_spare_price
-- ----------------------------
ALTER TABLE [dbo].[absen_log_spare_price] ADD PRIMARY KEY ([id])
;

-- ----------------------------
-- Indexes structure for table absen_offer
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table absen_offer
-- ----------------------------
ALTER TABLE [dbo].[absen_offer] ADD PRIMARY KEY ([id])
;

-- ----------------------------
-- Indexes structure for table absen_offer_free
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table absen_offer_free
-- ----------------------------
ALTER TABLE [dbo].[absen_offer_free] ADD PRIMARY KEY ([id])
;

-- ----------------------------
-- Indexes structure for table absen_offer_product
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table absen_offer_product
-- ----------------------------
ALTER TABLE [dbo].[absen_offer_product] ADD PRIMARY KEY ([id])
;

-- ----------------------------
-- Indexes structure for table absen_offer_service
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table absen_offer_service
-- ----------------------------
ALTER TABLE [dbo].[absen_offer_service] ADD PRIMARY KEY ([id])
;

-- ----------------------------
-- Indexes structure for table absen_offer_spare
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table absen_offer_spare
-- ----------------------------
ALTER TABLE [dbo].[absen_offer_spare] ADD PRIMARY KEY ([id])
;

-- ----------------------------
-- Indexes structure for table absen_offer_standard
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table absen_offer_standard
-- ----------------------------
ALTER TABLE [dbo].[absen_offer_standard] ADD PRIMARY KEY ([id])
;

-- ----------------------------
-- Indexes structure for table absen_org_department
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table absen_org_department
-- ----------------------------
ALTER TABLE [dbo].[absen_org_department] ADD PRIMARY KEY ([id])
;

-- ----------------------------
-- Indexes structure for table absen_org_department_lang
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table absen_org_department_lang
-- ----------------------------
ALTER TABLE [dbo].[absen_org_department_lang] ADD PRIMARY KEY ([id])
;

-- ----------------------------
-- Indexes structure for table absen_org_department_user
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table absen_org_department_user
-- ----------------------------
ALTER TABLE [dbo].[absen_org_department_user] ADD PRIMARY KEY ([id])
;

-- ----------------------------
-- Indexes structure for table absen_org_resource
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table absen_org_resource
-- ----------------------------
ALTER TABLE [dbo].[absen_org_resource] ADD PRIMARY KEY ([id])
;

-- ----------------------------
-- Indexes structure for table absen_org_resource_lang
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table absen_org_resource_lang
-- ----------------------------
ALTER TABLE [dbo].[absen_org_resource_lang] ADD PRIMARY KEY ([id])
;

-- ----------------------------
-- Indexes structure for table absen_org_role
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table absen_org_role
-- ----------------------------
ALTER TABLE [dbo].[absen_org_role] ADD PRIMARY KEY ([id])
;

-- ----------------------------
-- Indexes structure for table absen_org_role_lang
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table absen_org_role_lang
-- ----------------------------
ALTER TABLE [dbo].[absen_org_role_lang] ADD PRIMARY KEY ([id])
;

-- ----------------------------
-- Indexes structure for table absen_org_role_resource
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table absen_org_role_resource
-- ----------------------------
ALTER TABLE [dbo].[absen_org_role_resource] ADD PRIMARY KEY ([id])
;

-- ----------------------------
-- Indexes structure for table absen_org_role_user
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table absen_org_role_user
-- ----------------------------
ALTER TABLE [dbo].[absen_org_role_user] ADD PRIMARY KEY ([id])
;

-- ----------------------------
-- Indexes structure for table absen_org_user
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table absen_org_user
-- ----------------------------
ALTER TABLE [dbo].[absen_org_user] ADD PRIMARY KEY ([id])
;

-- ----------------------------
-- Indexes structure for table absen_org_user_lang
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table absen_org_user_lang
-- ----------------------------
ALTER TABLE [dbo].[absen_org_user_lang] ADD PRIMARY KEY ([id])
;

-- ----------------------------
-- Indexes structure for table absen_org_user_resource
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table absen_org_user_resource
-- ----------------------------
ALTER TABLE [dbo].[absen_org_user_resource] ADD PRIMARY KEY ([id])
;

-- ----------------------------
-- Indexes structure for table absen_product
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table absen_product
-- ----------------------------
ALTER TABLE [dbo].[absen_product] ADD PRIMARY KEY ([id])
;

-- ----------------------------
-- Indexes structure for table absen_product_box
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table absen_product_box
-- ----------------------------
ALTER TABLE [dbo].[absen_product_box] ADD PRIMARY KEY ([id])
;

-- ----------------------------
-- Indexes structure for table absen_product_certification
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table absen_product_certification
-- ----------------------------
ALTER TABLE [dbo].[absen_product_certification] ADD PRIMARY KEY ([id])
;

-- ----------------------------
-- Indexes structure for table absen_product_configuration
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table absen_product_configuration
-- ----------------------------
ALTER TABLE [dbo].[absen_product_configuration] ADD PRIMARY KEY ([id])
;

-- ----------------------------
-- Indexes structure for table absen_product_images
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table absen_product_images
-- ----------------------------
ALTER TABLE [dbo].[absen_product_images] ADD PRIMARY KEY ([id])
;

-- ----------------------------
-- Indexes structure for table absen_product_lang
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table absen_product_lang
-- ----------------------------
ALTER TABLE [dbo].[absen_product_lang] ADD PRIMARY KEY ([id])
;

-- ----------------------------
-- Indexes structure for table absen_product_modual
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table absen_product_modual
-- ----------------------------
ALTER TABLE [dbo].[absen_product_modual] ADD PRIMARY KEY ([id])
;

-- ----------------------------
-- Indexes structure for table absen_product_params
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table absen_product_params
-- ----------------------------
ALTER TABLE [dbo].[absen_product_params] ADD PRIMARY KEY ([id])
;

-- ----------------------------
-- Indexes structure for table absen_product_params_lang
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table absen_product_params_lang
-- ----------------------------
ALTER TABLE [dbo].[absen_product_params_lang] ADD PRIMARY KEY ([id])
;

-- ----------------------------
-- Indexes structure for table absen_product_price
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table absen_product_price
-- ----------------------------
ALTER TABLE [dbo].[absen_product_price] ADD PRIMARY KEY ([id])
;

-- ----------------------------
-- Indexes structure for table absen_product_sales
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table absen_product_sales
-- ----------------------------
ALTER TABLE [dbo].[absen_product_sales] ADD PRIMARY KEY ([id])
;

-- ----------------------------
-- Indexes structure for table absen_product_series
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table absen_product_series
-- ----------------------------
ALTER TABLE [dbo].[absen_product_series] ADD PRIMARY KEY ([id])
;

-- ----------------------------
-- Indexes structure for table absen_product_series_lang
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table absen_product_series_lang
-- ----------------------------
ALTER TABLE [dbo].[absen_product_series_lang] ADD PRIMARY KEY ([id])
;

-- ----------------------------
-- Indexes structure for table absen_product_series_standard
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table absen_product_series_standard
-- ----------------------------
ALTER TABLE [dbo].[absen_product_series_standard] ADD PRIMARY KEY ([id])
;

-- ----------------------------
-- Indexes structure for table absen_product_spare
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table absen_product_spare
-- ----------------------------
ALTER TABLE [dbo].[absen_product_spare] ADD PRIMARY KEY ([id])
;

-- ----------------------------
-- Indexes structure for table absen_product_spare_lang
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table absen_product_spare_lang
-- ----------------------------
ALTER TABLE [dbo].[absen_product_spare_lang] ADD PRIMARY KEY ([id])
;

-- ----------------------------
-- Indexes structure for table absen_product_spare_price
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table absen_product_spare_price
-- ----------------------------
ALTER TABLE [dbo].[absen_product_spare_price] ADD PRIMARY KEY ([id])
;

-- ----------------------------
-- Indexes structure for table absen_product_special
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table absen_product_special
-- ----------------------------
ALTER TABLE [dbo].[absen_product_special] ADD PRIMARY KEY ([id])
;

-- ----------------------------
-- Indexes structure for table absen_product_standard
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table absen_product_standard
-- ----------------------------
ALTER TABLE [dbo].[absen_product_standard] ADD PRIMARY KEY ([id])
;

-- ----------------------------
-- Indexes structure for table absen_sales_address
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table absen_sales_address
-- ----------------------------
ALTER TABLE [dbo].[absen_sales_address] ADD PRIMARY KEY ([id])
;

-- ----------------------------
-- Indexes structure for table absen_sales_customer
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table absen_sales_customer
-- ----------------------------
ALTER TABLE [dbo].[absen_sales_customer] ADD PRIMARY KEY ([id])
;

-- ----------------------------
-- Indexes structure for table absen_sales_customer_user
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table absen_sales_customer_user
-- ----------------------------
ALTER TABLE [dbo].[absen_sales_customer_user] ADD PRIMARY KEY ([id])
;

-- ----------------------------
-- Indexes structure for table absen_sales_levels
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table absen_sales_levels
-- ----------------------------
ALTER TABLE [dbo].[absen_sales_levels] ADD PRIMARY KEY ([id])
;

-- ----------------------------
-- Indexes structure for table absen_sales_levels_lang
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table absen_sales_levels_lang
-- ----------------------------
ALTER TABLE [dbo].[absen_sales_levels_lang] ADD PRIMARY KEY ([id]);
ALTER TABLE [dbo].[absen_sys_attachment] ADD PRIMARY KEY ([id]);

-- ----------------------------
-- Foreign Key structure for table [dbo].[absen_basic_dict_lang]
-- ----------------------------
ALTER TABLE [dbo].[absen_basic_dict_lang] ADD FOREIGN KEY ([dict]) REFERENCES [dbo].[absen_basic_dict] ([id]) ON DELETE NO ACTION ON UPDATE NO ACTION
;

-- ----------------------------
-- Foreign Key structure for table [dbo].[absen_file_connect_label]
-- ----------------------------
ALTER TABLE [dbo].[absen_file_connect_label] ADD FOREIGN KEY ([label]) REFERENCES [dbo].[absen_file_labels] ([id]) ON DELETE NO ACTION ON UPDATE NO ACTION
;

-- ----------------------------
-- Foreign Key structure for table [dbo].[absen_file_package_security_role]
-- ----------------------------
ALTER TABLE [dbo].[absen_file_package_security_role] ADD FOREIGN KEY ([role]) REFERENCES [dbo].[absen_org_role] ([id]) ON DELETE NO ACTION ON UPDATE NO ACTION
;

-- ----------------------------
-- Foreign Key structure for table [dbo].[absen_log_product]
-- ----------------------------
ALTER TABLE [dbo].[absen_log_product] ADD FOREIGN KEY ([product]) REFERENCES [dbo].[absen_product] ([id]) ON DELETE NO ACTION ON UPDATE NO ACTION
;

-- ----------------------------
-- Foreign Key structure for table [dbo].[absen_log_product_detail]
-- ----------------------------
ALTER TABLE [dbo].[absen_log_product_detail] ADD FOREIGN KEY ([log_id]) REFERENCES [dbo].[absen_log_product] ([id]) ON DELETE NO ACTION ON UPDATE NO ACTION
;

-- ----------------------------
-- Foreign Key structure for table [dbo].[absen_offer_free]
-- ----------------------------
ALTER TABLE [dbo].[absen_offer_free] ADD FOREIGN KEY ([offer]) REFERENCES [dbo].[absen_offer] ([id]) ON DELETE NO ACTION ON UPDATE NO ACTION
;

-- ----------------------------
-- Foreign Key structure for table [dbo].[absen_org_department_lang]
-- ----------------------------
ALTER TABLE [dbo].[absen_org_department_lang] ADD FOREIGN KEY ([department]) REFERENCES [dbo].[absen_org_department] ([id]) ON DELETE NO ACTION ON UPDATE NO ACTION
;

-- ----------------------------
-- Foreign Key structure for table [dbo].[absen_product]
-- ----------------------------
ALTER TABLE [dbo].[absen_product] ADD FOREIGN KEY ([configuration]) REFERENCES [dbo].[absen_product_configuration] ([id]) ON DELETE NO ACTION ON UPDATE NO ACTION
;

-- ----------------------------
-- Foreign Key structure for table [dbo].[absen_sales_customer]
-- ----------------------------
ALTER TABLE [dbo].[absen_sales_customer] ADD FOREIGN KEY ([billing]) REFERENCES [dbo].[absen_sales_address] ([id]) ON DELETE NO ACTION ON UPDATE NO ACTION
;

-- ----------------------------
-- Foreign Key structure for table [dbo].[absen_sales_customer_user]
-- ----------------------------
ALTER TABLE [dbo].[absen_sales_customer_user] ADD FOREIGN KEY ([user]) REFERENCES [dbo].[absen_org_user] ([id]) ON DELETE NO ACTION ON UPDATE NO ACTION
;

create table absen_sys_information (
   id                   int                  not null,
   "user"               int                  not null,
   title                varchar(500)         not null,
   contents             text                 null,
   type                 smallint             not null,
   obj_id               int                  null,
   readed               smallint             null,
   constraint PK_ABSEN_SYS_INFORMATION primary key (id)
);

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('absen_sys_information')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'type')
)
begin
   declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_dropextendedproperty 'MS_Description',
   'user', @CurrentUser, 'table', 'absen_sys_information', 'column', 'type'

end

select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description',
   '1. 审批推送 ;2 产品推送 ; 3.其他',
   'user', @CurrentUser, 'table', 'absen_sys_information', 'column', 'type';

CREATE TABLE [dbo].[absen_sys_information] (
[id] int NOT NULL identity(1,1),
[user] int NOT NULL ,
[title] varchar(500) NOT NULL ,
[contents] text NULL ,
[type] smallint NOT NULL ,
[obj_id] int NULL ,
[readed] smallint NULL
);

IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description',
'SCHEMA', N'dbo',
'TABLE', N'absen_sys_information',
'COLUMN', N'type')) > 0)
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'1. 审批推送 ;2 产品推送 ; 3.其他'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'absen_sys_information'
, @level2type = 'COLUMN', @level2name = N'type'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'1. 审批推送 ;2 产品推送 ; 3.其他'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'absen_sys_information'
, @level2type = 'COLUMN', @level2name = N'type';


CREATE TABLE [dbo].[absen_sys_language] (
[id] int NOT NULL IDENTITY(1,1) ,
[name] varchar(50) NOT NULL ,
[code] varchar(5) NOT NULL ,
[icon] varchar(200) NOT NULL ,
[order] smallint NULL
);

ALTER TABLE [dbo].[absen_sys_information] ADD PRIMARY KEY ([id]);

ALTER TABLE [dbo].[absen_sys_language] ADD PRIMARY KEY ([id]);


create table absen_sys_exhibition (
   id                   int  not null  identity(1,1),
   img                  varchar(200)         not null,
   orders               smallint             not null,
   attachment           bigint               null,
   url                  varchar(200)         null,
   smallint             char(10)             not null,
   constraint PK_ABSEN_SYS_EXHIBITION primary key (id)
);