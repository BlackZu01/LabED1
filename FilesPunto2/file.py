import time

start_time = time.time()
file = open("finaldata.csv" , "r")
read = file.readlines()
modified = []

for line in read:
   if line not in modified:
    modified.append(line.strip())

print(modified)
print("--- %s seconds ---" % (time.time() - start_time))
