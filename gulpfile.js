var gulp = require('gulp'),
    jshint = require('gulp-jshint'),
    livereload = require('gulp-livereload'),
    gp_concat = require('gulp-concat'),
    gp_rename = require('gulp-rename'),
    gp_uglify = require('gulp-uglify'),
    connect = require('gulp-connect'),
    protractor = require("gulp-protractor").protractor;

var paths = {
  webapp : 'src/main/webapp/',
  src_js : 'src/main/webapp/js/**/*.js',
  src_css : 'src/main/webapp/css/**/*.css',
  src_html : 'src/main/webapp/html/**/*.html',
  lib_js : ['node_modules/angular/angular.min.js',
      'node_modules/angular-ui-router/build/angular-ui-router.min.js'],
  lib_css : ['node_modules/bootstrap/dist/css/bootstrap.min.css'],
  target_js : 'src/main/webapp/public/js/',
  target_css : 'src/main/webapp/public/css/',
  target_html : 'src/main/webapp/public/html/'
};

gulp.task('jshint', function () {
  return gulp.src(['gulpfile.js', paths.src_js])
    .pipe(jshint())
    .pipe(jshint.reporter('default'));
});

gulp.task('js', function(){
  return gulp.src(paths.lib_js.concat(paths.src_js))
    .pipe(gp_concat('app.concat.js'))
    //.pipe(gp_rename('app.min.js'))
    //.pipe(gp_uglify())
    .pipe(gulp.dest(paths.target_js));
});

gulp.task('css', function() {
  return gulp.src(paths.lib_css.concat(paths.src_css))
    .pipe(gp_concat('style.concat.css'))
    //.pipe(gp_rename('style.min.css'))
    .pipe(gulp.dest(paths.target_css));
});

gulp.task('html', function() {
  return gulp.src(paths.src_html)
    .pipe(gulp.dest(paths.target_html));
});

gulp.task('connect', function() {
  connect.server({
    root: 'src/main/webapp',
    livereload: true,
    port: 8081
  });
});

gulp.task('watch', function () {
  gulp.watch([paths.webapp + '**/*', '!' + paths.webapp + '{public,public/**}'], ['build']);
  gulp.watch(paths.webapp + '**/*', function() {
    return gulp.src(paths.webapp)
      .pipe(connect.reload());
  });
});

gulp.task('build', ['js', 'css', 'html']);
gulp.task('run', ['connect', 'watch']);
gulp.task('default', ['jshint', 'build']);
