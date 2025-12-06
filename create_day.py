#!/usr/bin/env python3
import sys
import os
import json
import urllib.request
import shutil

n = len(sys.argv)

code_template = "template/DayX.java"
unit_test_template = "template/DayXTest.java"

srcRoot = "src/main/java"
testSrcRoot = "src/test/java"
testResourceRoot = "src/test/resources"

test_folder_token = 'dayx/'
package_token = 'dayx'
class_token = 'DayX'
class_token_test = 'DayXTest'

config_file = "config.json"
config_year_tag = "year"
config_session_cookie_tag = "session_cookie"
config = {}

# Create config file if it doesn't exists
if not os.path.isfile(config_file):
    year = input("There is no configuration defined.  Enter the year of the challenge: ")
    session_cookie = input("Enter session cookie: ")

    config = {config_year_tag: year, config_session_cookie_tag: session_cookie}

    with open(config_file, "w") as out_file:
        json.dump(config, out_file)

with open(config_file, "r") as in_file:
    config = json.load(in_file)

year = config[config_year_tag]
session_cookie = config[config_session_cookie_tag]

print(f"Year is set to {year}.  Input files will be download from that event.")

if n != 3:
    print("Error - must provide the problem day number and name")
    print(f"E.g., ./create_day.py 5 MonkeySee")
    print("Which will create a directory called day05, and Java files MonkeySee.java")
    print("and MonkeySeeTest.java")
    print("Note, the directory must not already exist")
    exit()

day = sys.argv[1]
padded_day = day.zfill(2)
yr_package = "yr" + year
directory = "day" + day.zfill(2)
srcDirectory = srcRoot + "/" + yr_package + "/" + directory
tstDirectory = testSrcRoot + "/" + yr_package + "/" + directory
tstResourceDirectory = testResourceRoot + "/" + yr_package + "/" + directory
name = sys.argv[2]

print(f"Name: {name}, directory: {directory}")    

if os.path.exists(srcDirectory):
    print(f"Error - directory {srcDirectory} already exists")
    exit()

if os.path.exists(tstDirectory):
    print(f"Error - directory {tstDirectory} already exists")
    exit()

if os.path.exists(tstResourceDirectory):
    print(f"Error - directory {tstResourceDirectory} already exists")
    exit()

if not os.path.exists(code_template) or not os.path.exists(unit_test_template):
    print(f"Error - ensure template files {code_template} and {unit_test_template} exist")
    exit()

# Create the directory for the day we are working on
os.makedirs(srcDirectory)
os.makedirs(tstDirectory)
os.makedirs(tstResourceDirectory)

# Create implementation file
implementation_file = srcDirectory + '/' + name + '.java'

with open(code_template, 'r') as input_file, open(implementation_file, 'w') as output_file:
    for line in input_file:
        line = line.replace(package_token, yr_package + "." + directory)
        line = line.replace(class_token, name)

        output_file.write(line)

# Create unit test file
unit_test_file = tstDirectory + '/' + name + 'Test.java'

with open(unit_test_template, 'r') as input_file, open(unit_test_file, 'w') as output_file:
    for line in input_file:
        line = line.replace(test_folder_token, yr_package + '/' + directory + '/')
        line = line.replace(package_token, yr_package + "." + directory)
        line = line.replace(class_token, name)
        line = line.replace(class_token_test, name + 'Test')

        output_file.write(line)

# Copy the input.txt file.  Note that some puzzles do not have an input.txt file.
# Although the majority do.
# Format: https://adventofcode.com/2023/day/7/input
input_file_url = f"https://adventofcode.com/{year}/day/{day}/input"
request = urllib.request.Request(input_file_url)
request.add_header("Cookie", "session=" + session_cookie)

with urllib.request.urlopen(request) as response:
    html_response = response.read()
    encoding = response.headers.get_content_charset('utf-8')
    decoded_html = html_response.decode(encoding)

    with open(tstResourceDirectory + '/input.txt', 'w') as output_file:
        output_file.write(decoded_html)