####################################################################

#Project    : HW_Midterm_20211031 (#3)
#Purpose    : EDA Analysis on COVID19_v4.CSV dataset
#First Name : DAKSH
#Last Name  : BHUVA
#CWID       : 10475468
#Date       : 10/31/2021

####################################################################
rm(list=ls())
cat("\014")

# Select the file from File Dialogue Box
file_name <- file.choose()
# Convert '?' into NA  
data <- read.csv(file_name)

# I.Summarizing each column (e.g. min, max, mean )
summary(data)

### II.Identifying missing values
  ## ?is.na
    # Total number of rows with NA values
    sum(is.na(data))
    # Total number of rows from each column with NA values
    colSums(is.na(data))
    # Which rows have NA values in column 'Age' and 'MonthAtHospital'
    which(is.na(data$Age))
    which(is.na(data$MonthAtHospital))

#install.packages("modeest")
library(modeest)
#?mlv

#mlv(data$Age)
#mlv(data$MonthAtHospital)

### III.Replacing the numerical missing values with the “mode” of the corresponding columns
for(i in 1:ncol(data))
{
  data[is.na(data[,i]),i] <- mlv(data[,i], na.rm=TRUE)
}

View(data)

### IV. Displaying the scatter plot of “Age”, “Exposure” and “MonthAtHospital”, one pair at a time
pairs(data[c(2,3,6)], main = "COVID19 Healthcare Workers",
      pch = 21, bg = c("red", "green")[factor(data$Infected)])

### V. Showing box plots for columns: “Age”, and “MonthAtHospital” 
  ## ?par - To display all the plots in 1 row and 2 columns
  par(mfrow=c(1,2))

  boxplot(data$Age,main="Boxplot of Age",col = "magenta")
  boxplot(data$MonthAtHospital,main="Boxplot of MonthAtHospital",col = "cyan")
  