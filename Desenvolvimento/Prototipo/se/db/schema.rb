# encoding: UTF-8
# This file is auto-generated from the current state of the database. Instead
# of editing this file, please use the migrations feature of Active Record to
# incrementally modify your database, and then regenerate this schema definition.
#
# Note that this schema.rb definition is the authoritative source for your
# database schema. If you need to create the application database on another
# system, you should be using db:schema:load, not running all the migrations
# from scratch. The latter is a flawed and unsustainable approach (the more migrations
# you'll amass, the slower it'll run and the greater likelihood for issues).
#
# It's strongly recommended to check this file into your version control system.

ActiveRecord::Schema.define(:version => 20130702054154) do

  create_table "categories", :force => true do |t|
    t.integer  "category_id"
    t.string   "description"
    t.datetime "created_at",  :null => false
    t.datetime "updated_at",  :null => false
    t.integer  "tipo"
    t.integer  "status"
  end

  create_table "histories", :force => true do |t|
    t.integer  "patient_id"
    t.integer  "variable_id"
    t.datetime "created_at",  :null => false
    t.datetime "updated_at",  :null => false
  end

  create_table "patients", :force => true do |t|
    t.string   "nome"
    t.string   "cpf"
    t.date     "data_nasc"
    t.datetime "created_at",  :null => false
    t.datetime "updated_at",  :null => false
    t.integer  "sexo"
    t.string   "numRegistro"
  end

  create_table "physiotherapists", :force => true do |t|
    t.string   "nome"
    t.string   "login"
    t.string   "senha"
    t.datetime "created_at", :null => false
    t.datetime "updated_at", :null => false
  end

  create_table "valores", :force => true do |t|
    t.integer  "value_min"
    t.integer  "value_max"
    t.integer  "value"
    t.string   "description"
    t.datetime "created_at",  :null => false
    t.datetime "updated_at",  :null => false
    t.integer  "id_variable"
  end

  create_table "variables", :force => true do |t|
    t.integer  "category_id"
    t.string   "description"
    t.integer  "value"
    t.datetime "created_at",  :null => false
    t.datetime "updated_at",  :null => false
    t.integer  "status"
    t.integer  "tipo"
  end

end
