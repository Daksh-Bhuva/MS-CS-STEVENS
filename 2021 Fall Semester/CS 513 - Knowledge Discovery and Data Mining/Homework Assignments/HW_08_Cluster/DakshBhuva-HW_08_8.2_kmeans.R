####################################################################

#Project    : HW_08_8.2_kmeans
#Purpose    : kmeans cluster methodology on breast-cancer-wisconsin dataset
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

## Implementing kmeans methodology
  # ?kmeans

kmeans_2<- kmeans(data[,-1],2,nstart = 10)
# Cluster to which each point is allocated
kmeans_2$cluster
# Matrix of cluster centres
kmeans_2$centers

# Frequency table for predictions
table(kmeans_2$cluster,data[,1])

# Plotting the clusters
  library(cluster)

plotcluster(data[,-1], kmeans_2$cluster)
