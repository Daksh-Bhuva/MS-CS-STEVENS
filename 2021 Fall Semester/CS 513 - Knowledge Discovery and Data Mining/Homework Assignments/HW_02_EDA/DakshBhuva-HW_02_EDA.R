####################################################################

#Project : HW_02_EDA 
#Purpose : EDA analysis on breast-cancer-wisconsin dataset
#First Name : DAKSH
#Last Name : BHUVA
#CWID : 10475468
#Date : 10/11/2021

####################################################################
rm(list=ls())
cat("\014")

### 1-Load the “breast-cancer-wisconsin.data.csv” from canvas into R and perform the EDA analysis:
  ## data = read.csv("/Users/dakshbhuva/Desktop/CS-513 KDD/breast-cancer-wisconsin.csv", na.strings = "?")
    # Select the file from File Dialogue Box
    file_name <- file.choose()
    # Convert '?' into NA  
    data <- read.csv(file_name, na.strings = '?')

# View the loaded dataset
  View(data)
  
# I.Summarizing each column (e.g. min, max, mean )
  summary(data)

### II.Identifying missing values
  ## ?is.na
    # Total number of rows with NA values
    sum(is.na(data))
    # Total number of rows from each column with NA values
    colSums(is.na(data))
    # Which rows have NA values in column F6
    which(is.na(data$F6))


### III.Replacing the missing values with the “mean” of the column.
  ## ?replace
    for(i in 1:ncol(data))
    {
      data[is.na(data[,i]),i] <- mean(data[,i], na.rm=TRUE)
    }
    
  View(data)
  ## ?round
    # Rounding up the columns to 2 digits after decimal
    data[,-1] <-round(data[,-1],2)
    
### IV.Displaying the frequency table of “Class” vs. F6
  ## ?table ?ftable
    table(data$Class,data$F6)

### V.Displaying the scatter plot of F1 to F6, one pair at a time
  ## ?plot
    plot(data[,2:7],col="red",main="Scatter Plot of breast-cancer-wisconsin",)
  ## ?pairs will produce the same results
    pairs(data[,2:7],col="blue",main="Scatter Plot of breast-cancer-wisconsin",)
    
### VI.Show histogram box plot for columns F7 to F9
  ## ?plot.histogram
    # ?par - To display all the plots in 2 rows and 3 columns
    par(mfrow=c(2,3))
    # ?hist
    hist(data$F7, prob = TRUE, main = 'Histogram of F7', xlab = 'F7',col = "green")
    hist(data$F8, prob = TRUE, main = 'Histogram of F8', xlab = 'F8',col = "yellow")
    hist(data$F9, prob = TRUE, main = 'Histogram of F9', xlab = 'F9',col = "cyan")
    # ?boxplot
    boxplot(data$F7,main="Boxplot of F7",col = "green")
    boxplot(data$F8,main="Boxplot of F8",col = "yellow")
    boxplot(data$F9,main="Boxplot of F9",col = "cyan")

### 2-Delete all the objects from your R- environment. 
ls()
rm(list = ls())

### Reload the “breast-cancer-wisconsin.data.csv” from canvas into R. 
  ## data = read.csv("/Users/dakshbhuva/Desktop/CS-513 KDD/breast-cancer-wisconsin.csv", na.strings = "?")
    name <-file.choose()
    data <- read.csv(name, na.strings = '?')
    # Number of rows
    nrow(data)  
    
  ## Remove any row with a missing value in any of the columns.
    data = na.omit(data)
    # Number of rows after removing missing values
    nrow(data)
    View(data)
    