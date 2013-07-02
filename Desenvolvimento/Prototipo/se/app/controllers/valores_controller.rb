class ValoresController < ApplicationController
  # GET /valores
  # GET /valores.json
  def index
    @valores = Valore.all

    respond_to do |format|
      format.html # index.html.erb
      format.json { render json: @valores }
    end
  end

  # GET /valores/1
  # GET /valores/1.json
  def show
    @valore = Valore.find(params[:id])

    respond_to do |format|
      format.html # show.html.erb
      format.json { render json: @valore }
    end
  end

  # GET /valores/new
  # GET /valores/new.json
  def new
    @valore = Valore.new

    respond_to do |format|
      format.html # new.html.erb
      format.json { render json: @valore }
    end
  end

  # GET /valores/1/edit
  def edit
    @valore = Valore.find(params[:id])
  end

  # POST /valores
  # POST /valores.json
  def create
    @valore = Valore.new(params[:valore])

    respond_to do |format|
      if @valore.save
        format.html { redirect_to @valore, notice: 'Valore was successfully created.' }
        format.json { render json: @valore, status: :created, location: @valore }
      else
        format.html { render action: "new" }
        format.json { render json: @valore.errors, status: :unprocessable_entity }
      end
    end
  end

  # PUT /valores/1
  # PUT /valores/1.json
  def update
    @valore = Valore.find(params[:id])

    respond_to do |format|
      if @valore.update_attributes(params[:valore])
        format.html { redirect_to @valore, notice: 'Valore was successfully updated.' }
        format.json { head :no_content }
      else
        format.html { render action: "edit" }
        format.json { render json: @valore.errors, status: :unprocessable_entity }
      end
    end
  end

  # DELETE /valores/1
  # DELETE /valores/1.json
  def destroy
    @valore = Valore.find(params[:id])
    @valore.destroy

    respond_to do |format|
      format.html { redirect_to valores_url }
      format.json { head :no_content }
    end
  end
end
