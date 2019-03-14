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

Date: 2018-08-08 20:13:09
*/


-- ----------------------------
-- Table structure for absen_experience_comment
-- ----------------------------
DROP TABLE [dbo].[absen_experience_comment]
GO
CREATE TABLE [dbo].[absen_experience_comment] (
[id] bigint NOT NULL ,
[share_id] bigint NOT NULL ,
[content] varchar(2000) NOT NULL ,
[create_user] int NOT NULL ,
[create_time] datetime NOT NULL ,
[modify_user] int NULL ,
[modify_time] datetime NULL ,
[parent_id] bigint NOT NULL DEFAULT ((0)) ,
[status] smallint NOT NULL 
)


GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'absen_experience_comment', 
'COLUMN', N'share_id')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'分享信息表id 关联absen_experience_share表id'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'absen_experience_comment'
, @level2type = 'COLUMN', @level2name = N'share_id'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'分享信息表id 关联absen_experience_share表id'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'absen_experience_comment'
, @level2type = 'COLUMN', @level2name = N'share_id'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'absen_experience_comment', 
'COLUMN', N'content')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'评论或者回复内容'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'absen_experience_comment'
, @level2type = 'COLUMN', @level2name = N'content'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'评论或者回复内容'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'absen_experience_comment'
, @level2type = 'COLUMN', @level2name = N'content'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'absen_experience_comment', 
'COLUMN', N'create_user')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'创建人'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'absen_experience_comment'
, @level2type = 'COLUMN', @level2name = N'create_user'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'创建人'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'absen_experience_comment'
, @level2type = 'COLUMN', @level2name = N'create_user'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'absen_experience_comment', 
'COLUMN', N'create_time')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'创建时间'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'absen_experience_comment'
, @level2type = 'COLUMN', @level2name = N'create_time'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'创建时间'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'absen_experience_comment'
, @level2type = 'COLUMN', @level2name = N'create_time'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'absen_experience_comment', 
'COLUMN', N'modify_user')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'修改人'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'absen_experience_comment'
, @level2type = 'COLUMN', @level2name = N'modify_user'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'修改人'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'absen_experience_comment'
, @level2type = 'COLUMN', @level2name = N'modify_user'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'absen_experience_comment', 
'COLUMN', N'modify_time')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'修改时间'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'absen_experience_comment'
, @level2type = 'COLUMN', @level2name = N'modify_time'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'修改时间'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'absen_experience_comment'
, @level2type = 'COLUMN', @level2name = N'modify_time'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'absen_experience_comment', 
'COLUMN', N'parent_id')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'评论父id，为0代表对经验分享的评论，非0代表对评论的回复'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'absen_experience_comment'
, @level2type = 'COLUMN', @level2name = N'parent_id'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'评论父id，为0代表对经验分享的评论，非0代表对评论的回复'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'absen_experience_comment'
, @level2type = 'COLUMN', @level2name = N'parent_id'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'absen_experience_comment', 
'COLUMN', N'status')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'状态 1.通过 2.未通过'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'absen_experience_comment'
, @level2type = 'COLUMN', @level2name = N'status'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'状态 1.通过 2.未通过'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'absen_experience_comment'
, @level2type = 'COLUMN', @level2name = N'status'
GO

-- ----------------------------
-- Indexes structure for table absen_experience_comment
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table absen_experience_comment
-- ----------------------------
ALTER TABLE [dbo].[absen_experience_comment] ADD PRIMARY KEY ([id])
GO
