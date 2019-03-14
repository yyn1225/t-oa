/*
Navicat SQL Server Data Transfer

Source Server         : localhost_1433
Source Server Version : 105000
Source Host           : localhost:1433
Source Database       : XBTest2
Source Schema         : dbo

Target Server Type    : SQL Server
Target Server Version : 105000
File Encoding         : 65001

Date: 2018-08-08 19:24:05
*/


-- ----------------------------
-- Table structure for absen_offer_spares_formula
-- ----------------------------
DROP TABLE [dbo].[absen_offer_spares_formula]
GO
CREATE TABLE [dbo].[absen_offer_spares_formula] (
[id] bigint NOT NULL ,
[offer] bigint NOT NULL ,
[panel] bigint NOT NULL ,
[spare] bigint NULL ,
[spel_two] varchar(255) NULL ,
[spel_three] varchar(255) NULL ,
[spel_four] varchar(255) NULL ,
[spel_five] varchar(255) NULL ,
[type_two] int NULL ,
[count_two] decimal(18,6) NULL ,
[type_three] int NULL ,
[count_three] decimal(18,6) NULL ,
[type_four] int NULL ,
[count_four] decimal(18,6) NULL ,
[type_five] int NULL ,
[count_five] decimal(18,6) NULL 
)


GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'absen_offer_spares_formula', 
'COLUMN', N'offer')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'报价单id'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'absen_offer_spares_formula'
, @level2type = 'COLUMN', @level2name = N'offer'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'报价单id'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'absen_offer_spares_formula'
, @level2type = 'COLUMN', @level2name = N'offer'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'absen_offer_spares_formula', 
'COLUMN', N'panel')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'报价屏体id'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'absen_offer_spares_formula'
, @level2type = 'COLUMN', @level2name = N'panel'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'报价屏体id'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'absen_offer_spares_formula'
, @level2type = 'COLUMN', @level2name = N'panel'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'absen_offer_spares_formula', 
'COLUMN', N'spare')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'备件id'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'absen_offer_spares_formula'
, @level2type = 'COLUMN', @level2name = N'spare'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'备件id'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'absen_offer_spares_formula'
, @level2type = 'COLUMN', @level2name = N'spare'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'absen_offer_spares_formula', 
'COLUMN', N'spel_two')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'两年计算公式'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'absen_offer_spares_formula'
, @level2type = 'COLUMN', @level2name = N'spel_two'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'两年计算公式'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'absen_offer_spares_formula'
, @level2type = 'COLUMN', @level2name = N'spel_two'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'absen_offer_spares_formula', 
'COLUMN', N'spel_three')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'三年计算公式'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'absen_offer_spares_formula'
, @level2type = 'COLUMN', @level2name = N'spel_three'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'三年计算公式'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'absen_offer_spares_formula'
, @level2type = 'COLUMN', @level2name = N'spel_three'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'absen_offer_spares_formula', 
'COLUMN', N'spel_four')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'四年计算公式'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'absen_offer_spares_formula'
, @level2type = 'COLUMN', @level2name = N'spel_four'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'四年计算公式'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'absen_offer_spares_formula'
, @level2type = 'COLUMN', @level2name = N'spel_four'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'absen_offer_spares_formula', 
'COLUMN', N'spel_five')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'五年计算公式'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'absen_offer_spares_formula'
, @level2type = 'COLUMN', @level2name = N'spel_five'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'五年计算公式'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'absen_offer_spares_formula'
, @level2type = 'COLUMN', @level2name = N'spel_five'
GO

-- ----------------------------
-- Indexes structure for table absen_offer_spares_formula
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table absen_offer_spares_formula
-- ----------------------------
ALTER TABLE [dbo].[absen_offer_spares_formula] ADD PRIMARY KEY ([id])
GO
