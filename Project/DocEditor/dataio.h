#pragma once
int importData(char *doc, const size_t doclen, char *path);
int saveData(char *doc, const size_t doclen, const char *path);
int editData(char *doc, const size_t doclen);
int analyzeData(char *doc, struct words *resault);