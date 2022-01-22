####################################################################

#Project    : HW_08_8.1_hclust
#Purpose    : hclust cluster methodology on breast-cancer-wisconsin dataset
#First Name : DAKSH
#Last Name  : BHUVA
#CWID       : 10475468
#Date       : 11/22/2021

####################################################################
rm(list=ls())
cat("\014")

# Select the file from File Dialogue Box
file_name <- file.choose()

# Convert '?' into NA 
data <- read.csv(file_name, na.strings = '?')

# Remove the missing values
data<- na.omit(data)

# Removing the column 'id' as it is not required
data<-data[-1]

## View the dataset
  # View(data)

## Implementing hclust methodology
  # ?hclust
  # ?dist
  # ?cutree

# Distance Matrix Computation
bcw_dist <- dist(data[,-1])
# Hierarchical Clustering
hclust_resutls<-hclust(bcw_dist)
plot(hclust_resutls)

# Cutting the Tree into 2 Clusters
hclust_2<-cutree(hclust_resutls,2)

# Frequency table for predictions
table(hclust_2,data[,1])

# Plotting the clusters
  library(fpc)

?plotcluster(data[,-1], hclust_2)

