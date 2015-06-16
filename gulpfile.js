var gulp = require('gulp'),
    jshint = require('gulp-jshint'),
    livereload = require('gulp-livereload'),
    gp_concat = require('gulp-concat'),
    gp_rename = require('gulp-rename'),
    gp_uglify = require('gulp-uglify'),
    connect = require('gulp-connect'),
    protractor = require("gulp-protractor").protractor,
    webdriver_standalone = require("gulp-protractor").webdriver_standalone,
    webdriver_update = require('gulp-protractor').webdriver_update;

var webappPath     = 'src/main/webapp/',
    resourcesPath  = 'src/main/resources/',
    generatedPath  = 'target/classes/';

gulp.task('lint', function () {
    return gulp.src('public/js/**/*.js')
        .pipe(jshint())
        .pipe(jshint.reporter('default'));
});

gulp.task('build', function(){
    return gulp.src(['node_modules/angular/angular.min.js', 'node_modules/angular-ui-router/build/angular-ui-router.min.js'])
        .pipe(gp_concat('concat.js'))
        .pipe(gp_rename('gulp-maven.min.js'))
        /*.pipe(gp_uglify())*/
        .pipe(gulp.dest(webappPath + 'js/'));
});

gulp.task('connect', function() {
    connect.server({
        root: 'src/main/webapp',
        livereload: true,
        port: 8081
    });
});

gulp.task('start', ['connect']);
gulp.task('default', ['lint', 'build']);