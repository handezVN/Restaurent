USE [master]
GO
/****** Object:  Database [Restaurent]    Script Date: 2/9/2021 6:10:08 PM ******/
CREATE DATABASE [Restaurent]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'Restaurent', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.SQLEXPRESS\MSSQL\DATA\Restaurent.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'Restaurent_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.SQLEXPRESS\MSSQL\DATA\Restaurent_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
 WITH CATALOG_COLLATION = DATABASE_DEFAULT
GO
ALTER DATABASE [Restaurent] SET COMPATIBILITY_LEVEL = 150
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [Restaurent].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [Restaurent] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [Restaurent] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [Restaurent] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [Restaurent] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [Restaurent] SET ARITHABORT OFF 
GO
ALTER DATABASE [Restaurent] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [Restaurent] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [Restaurent] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [Restaurent] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [Restaurent] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [Restaurent] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [Restaurent] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [Restaurent] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [Restaurent] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [Restaurent] SET  DISABLE_BROKER 
GO
ALTER DATABASE [Restaurent] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [Restaurent] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [Restaurent] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [Restaurent] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [Restaurent] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [Restaurent] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [Restaurent] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [Restaurent] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [Restaurent] SET  MULTI_USER 
GO
ALTER DATABASE [Restaurent] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [Restaurent] SET DB_CHAINING OFF 
GO
ALTER DATABASE [Restaurent] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [Restaurent] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [Restaurent] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [Restaurent] SET QUERY_STORE = OFF
GO
USE [Restaurent]
GO
/****** Object:  Table [dbo].[DetailOrderTbl]    Script Date: 2/9/2021 6:10:09 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[DetailOrderTbl](
	[Order_id] [nchar](10) NULL,
	[name] [varchar](max) NULL,
	[quantity] [int] NULL,
	[price] [int] NULL,
	[Product_id] [nchar](10) NULL
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[DetailTableTbl]    Script Date: 2/9/2021 6:10:09 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[DetailTableTbl](
	[Table_ID] [nchar](10) NULL,
	[Product_id] [nchar](10) NULL,
	[number] [nchar](11) NULL,
	[quantity] [int] NULL,
	[price] [int] NULL,
	[name] [varchar](max) NULL
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[OrderTbl]    Script Date: 2/9/2021 6:10:09 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[OrderTbl](
	[Order_id] [nchar](10) NOT NULL,
	[number] [nchar](11) NULL,
	[date] [datetime] NULL,
	[total] [int] NULL,
	[Table_ID] [nchar](10) NULL,
 CONSTRAINT [PK_OrderTbl] PRIMARY KEY CLUSTERED 
(
	[Order_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[ProductTbl]    Script Date: 2/9/2021 6:10:09 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ProductTbl](
	[Name] [varchar](max) NULL,
	[Price] [nchar](10) NULL,
	[Product_id] [nchar](10) NOT NULL,
 CONSTRAINT [PK_ProductTbl] PRIMARY KEY CLUSTERED 
(
	[Product_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[TableTbl]    Script Date: 2/9/2021 6:10:09 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[TableTbl](
	[Table_ID] [nchar](10) NOT NULL,
	[Status] [bit] NULL,
 CONSTRAINT [PK_TabelTbl] PRIMARY KEY CLUSTERED 
(
	[Table_ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[UserTBL]    Script Date: 2/9/2021 6:10:09 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[UserTBL](
	[number] [nchar](11) NOT NULL,
	[password] [varchar](50) NULL,
	[name] [varchar](50) NULL,
	[gender] [bit] NULL,
	[isAdmin] [bit] NULL,
 CONSTRAINT [PK_User] PRIMARY KEY CLUSTERED 
(
	[number] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
ALTER TABLE [dbo].[DetailOrderTbl]  WITH CHECK ADD  CONSTRAINT [FK_DetailOrderTbl_OrderTbl] FOREIGN KEY([Order_id])
REFERENCES [dbo].[OrderTbl] ([Order_id])
GO
ALTER TABLE [dbo].[DetailOrderTbl] CHECK CONSTRAINT [FK_DetailOrderTbl_OrderTbl]
GO
ALTER TABLE [dbo].[DetailOrderTbl]  WITH CHECK ADD  CONSTRAINT [FK_DetailOrderTbl_ProductTbl] FOREIGN KEY([Product_id])
REFERENCES [dbo].[ProductTbl] ([Product_id])
GO
ALTER TABLE [dbo].[DetailOrderTbl] CHECK CONSTRAINT [FK_DetailOrderTbl_ProductTbl]
GO
ALTER TABLE [dbo].[DetailTableTbl]  WITH CHECK ADD  CONSTRAINT [FK_DetailTableTbl_ProductTbl] FOREIGN KEY([Product_id])
REFERENCES [dbo].[ProductTbl] ([Product_id])
GO
ALTER TABLE [dbo].[DetailTableTbl] CHECK CONSTRAINT [FK_DetailTableTbl_ProductTbl]
GO
ALTER TABLE [dbo].[DetailTableTbl]  WITH CHECK ADD  CONSTRAINT [FK_DetailTableTbl_TabelTbl] FOREIGN KEY([Table_ID])
REFERENCES [dbo].[TableTbl] ([Table_ID])
GO
ALTER TABLE [dbo].[DetailTableTbl] CHECK CONSTRAINT [FK_DetailTableTbl_TabelTbl]
GO
ALTER TABLE [dbo].[DetailTableTbl]  WITH CHECK ADD  CONSTRAINT [FK_DetailTableTbl_UserTBL] FOREIGN KEY([number])
REFERENCES [dbo].[UserTBL] ([number])
GO
ALTER TABLE [dbo].[DetailTableTbl] CHECK CONSTRAINT [FK_DetailTableTbl_UserTBL]
GO
ALTER TABLE [dbo].[OrderTbl]  WITH CHECK ADD  CONSTRAINT [FK_OrderTbl_UserTBL] FOREIGN KEY([number])
REFERENCES [dbo].[UserTBL] ([number])
GO
ALTER TABLE [dbo].[OrderTbl] CHECK CONSTRAINT [FK_OrderTbl_UserTBL]
GO
USE [master]
GO
ALTER DATABASE [Restaurent] SET  READ_WRITE 
GO
