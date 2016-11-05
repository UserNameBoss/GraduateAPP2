package com.example.yangxiaolong.graduateapp.domain;

/**
 * Created by yangxiaolong on 2016/11/4.
 */
public class Smilies {

        private int Height;
        private String Icon;
        private int SmiliesId;
        private String Name;
        private int Width;

        public int getHeight() {
            return Height;
        }

        public void setHeight(int height) {
            Height = height;
        }

        public String getIcon() {
            return Icon;
        }

        public void setIcon(String icon) {
            Icon = icon;
        }

        public String getName() {
            return Name;
        }

        public void setName(String name) {
            Name = name;
        }

        public int getSmiliesId() {
            return SmiliesId;
        }

        public void setSmiliesId(int smiliesId) {
            SmiliesId = smiliesId;
        }

        public int getWidth() {
            return Width;
        }

        public void setWidth(int width) {
            Width = width;
        }

        public Smilies(int width, String name, int smiliesId, String icon, int height) {
            Width = width;
            Name = name;
            SmiliesId = smiliesId;
            Icon = icon;
            Height = height;
        }
}
