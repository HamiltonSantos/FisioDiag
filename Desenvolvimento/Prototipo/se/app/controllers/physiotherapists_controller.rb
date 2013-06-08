class PhysiotherapistsController < ApplicationController
  # GET /physiotherapists
  # GET /physiotherapists.json
  def index
    @physiotherapists = Physiotherapist.all

    respond_to do |format|
      format.html # index.html.erb
      format.json { render json: @physiotherapists }
    end
  end

  # GET /physiotherapists/1
  # GET /physiotherapists/1.json
  def show
    @physiotherapist = Physiotherapist.find(params[:id])

    respond_to do |format|
      format.html # show.html.erb
      format.json { render json: @physiotherapist }
    end
  end

  # GET /physiotherapists/new
  # GET /physiotherapists/new.json
  def new
    @physiotherapist = Physiotherapist.new

    respond_to do |format|
      format.html # new.html.erb
      format.json { render json: @physiotherapist }
    end
  end

  # GET /physiotherapists/1/edit
  def edit
    @physiotherapist = Physiotherapist.find(params[:id])
  end

  # POST /physiotherapists
  # POST /physiotherapists.json
  def create
    @physiotherapist = Physiotherapist.new(params[:physiotherapist])

    respond_to do |format|
      if @physiotherapist.save
        format.html { redirect_to @physiotherapist, notice: 'Physiotherapist was successfully created.' }
        format.json { render json: @physiotherapist, status: :created, location: @physiotherapist }
      else
        format.html { render action: "new" }
        format.json { render json: @physiotherapist.errors, status: :unprocessable_entity }
      end
    end
  end

  # PUT /physiotherapists/1
  # PUT /physiotherapists/1.json
  def update
    @physiotherapist = Physiotherapist.find(params[:id])

    respond_to do |format|
      if @physiotherapist.update_attributes(params[:physiotherapist])
        format.html { redirect_to @physiotherapist, notice: 'Physiotherapist was successfully updated.' }
        format.json { head :no_content }
      else
        format.html { render action: "edit" }
        format.json { render json: @physiotherapist.errors, status: :unprocessable_entity }
      end
    end
  end

  # DELETE /physiotherapists/1
  # DELETE /physiotherapists/1.json
  def destroy
    @physiotherapist = Physiotherapist.find(params[:id])
    @physiotherapist.destroy

    respond_to do |format|
      format.html { redirect_to physiotherapists_url }
      format.json { head :no_content }
    end
  end
end
