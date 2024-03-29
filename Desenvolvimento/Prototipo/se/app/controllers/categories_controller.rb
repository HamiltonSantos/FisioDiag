class CategoriesController < ApplicationController


  # GET /categories
  # GET /categories.json
  def index
    @categories = Category.where(:status => 1)

    respond_to do |format|
      format.html # index.html.erb
      format.json { render json: @categories }
    end
  end

  # GET /categories/1
  # GET /categories/1.json
  def show
    if params[:id] == $id_intercorrencia.to_s
      intercorrencias
    elsif params[:id] == $id_ocorrencia.to_s
      ocorrencias
    else
      @category = Category.find(params[:id])
      @subcategory = Category.new(:category_id => @category.id, :status => 1)
      @variable = Variable.new(:category_id => @category.id)

      respond_to do |format|
        format.html # show.html.erb
        format.json { render json: @category }
      end
    end 
  end

  # GET /categories/new
  # GET /categories/new.json
  def new
    @category = Category.new(:status => 1)

    respond_to do |format|
      format.html # new.html.erb
      format.json { render json: @category }
    end
  end

  # GET /categories/1/edit
  def edit
    @category = Category.find(params[:id])
  end

  # POST /categories
  # POST /categories.json
  def create
    @category = Category.new(params[:category])

    respond_to do |format|
      if @category.save
        format.html { redirect_to Category.find(@category.id), notice: 'Category was successfully created.' }
        format.json { render json: @category, status: :created, location: @category }
      else
        format.html { render action: "new" }
        format.json { render json: @category.errors, status: :unprocessable_entity }
      end
    end
  end

  # PUT /categories/1
  # PUT /categories/1.json
  def update
    @category = Category.find(params[:id])

    respond_to do |format|
      if @category.update_attributes(params[:category])
        format.html { redirect_to @category, notice: 'Category was successfully updated.' }
        format.json { head :no_content }
      else
        format.html { render action: "edit" }
        format.json { render json: @category.errors, status: :unprocessable_entity }
      end
    end
  end

  # DELETE /categories/1
  # DELETE /categories/1.json
  def destroy
    @category = Category.find(params[:id])
    @category.destroy

    respond_to do |format|
      format.html { redirect_to categories_url }
      format.json { head :no_content }
    end
  end

  # /categories/sugerir
  def sugerir
    @category = Category.new(:status => 3)
    
  end

  # /categories/sugeridas
  def sugeridas
    @categories = Category.where(:status => 3)

    render 'index_sugeridas'
  end

  #### Intercorrencias

  # /categories/intercorrencias/new
  def new_intercorrencia
    @valore = Valore.new(:id_variable => $id_intercorrencia)
    render 'new_variable_intercorrencia'
  end
  # /categories/intercorrencias/sugeridas
  def sugeridas_intercorrencia
    render 'index_intercorrencias_sugeridas', :locals => { :variables => Variable.where(:category_id => $id_intercorrencia, :status => 3) }
  end
  # /categories/intercorrencias
  def intercorrencias
    @valores = Valore.where(:id_variable => $id_intercorrencia)
    render 'index_intercorrencias'
  end
  # /categories/intercorrencias/sugerir
  def sugerir_intercorrencia
    @variable = Variable.new(:category_id => $id_intercorrencia, :status => 3)
    render 'sugerir_intercorrencia'
  end

  #### Fim Intercorrencais
  #### Ocorrencias

  # /categories/ocorrencias/new
  def new_ocorrencia
    @valore = Valore.new(:id_variable => $id_ocorrencia)
    render 'new_variable_ocorrencia'
  end
  # /categories/ocorrencias/sugerir
  def sugerir_ocorrencia
    @variable = Variable.new(:category_id => $id_ocorrencia, :status => 3)
    render 'sugerir_ocorrencia'
  end
  # /categories/ocorrencias
  def ocorrencias
    @valores = Valore.where(:id_variable => $id_ocorrencia)
    render 'index_ocorrencias'
  end
  def ocorrencia_detalhes

    render 'detalhes_ocorrencias'
  end
  # /categories/ocorrencias/sugeridas
  def sugeridas_ocorrencia
    render 'index_ocorrencias_sugeridas', :locals => { :variables => Variable.where( :category_id => $id_ocorrencia, :status => 3) }
  end

  #### Fim Ocorrencias

end