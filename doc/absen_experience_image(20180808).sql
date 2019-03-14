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

Date: 2018-08-08 20:13:16
*/


-- ----------------------------
-- Table structure for absen_experience_image
-- ----------------------------
DROP TABLE [dbo].[absen_experience_image]
GO
CREATE TABLE [dbo].[absen_experience_image] (
[id] bigint NOT NULL ,
[share_id] bigint NOT NULL ,
[comment_id] bigint NULL ,
[url] varchar(100) NOT NULL ,
[create_user] int NOT NULL ,
[create_time] datetime NOT NULL ,
[file_name] varchar(200) NULL ,
[file_type] varchar(255) NULL ,
[file_path] varchar(255) NULL ,
[file_size] float(53) NULL 
)


GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'absen_experience_image', 
'COLUMN', N'id')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'主键'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'absen_experience_image'
, @level2type = 'COLUMN', @level2name = N'id'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'主键'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'absen_experience_image'
, @level2type = 'COLUMN', @level2name = N'id'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'absen_experience_image', 
'COLUMN', N'share_id')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'分享信息表id 关联absen_experience_share表id'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'absen_experience_image'
, @level2type = 'COLUMN', @level2name = N'share_id'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'分享信息表id 关联absen_experience_share表id'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'absen_experience_image'
, @level2type = 'COLUMN', @level2name = N'share_id'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'absen_experience_image', 
'COLUMN', N'comment_id')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'关联absen_experience_comment表id，可以为空，为空时为经验分享本身的图片，不为空时为评论的图片'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'absen_experience_image'
, @level2type = 'COLUMN', @level2name = N'comment_id'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'关联absen_experience_comment表id，可以为空，为空时为经验分享本身的图片，不为空时为评论的图片'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'absen_experience_image'
, @level2type = 'COLUMN', @level2name = N'comment_id'
GO

-- ----------------------------
-- Indexes structure for table absen_experience_image
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table absen_experience_image
-- ----------------------------
ALTER TABLE [dbo].[absen_experience_image] ADD PRIMARY KEY ([id])
GO
