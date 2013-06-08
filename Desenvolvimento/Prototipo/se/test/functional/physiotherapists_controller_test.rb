require 'test_helper'

class PhysiotherapistsControllerTest < ActionController::TestCase
  setup do
    @physiotherapist = physiotherapists(:one)
  end

  test "should get index" do
    get :index
    assert_response :success
    assert_not_nil assigns(:physiotherapists)
  end

  test "should get new" do
    get :new
    assert_response :success
  end

  test "should create physiotherapist" do
    assert_difference('Physiotherapist.count') do
      post :create, physiotherapist: { login: @physiotherapist.login, nome: @physiotherapist.nome, senha: @physiotherapist.senha }
    end

    assert_redirected_to physiotherapist_path(assigns(:physiotherapist))
  end

  test "should show physiotherapist" do
    get :show, id: @physiotherapist
    assert_response :success
  end

  test "should get edit" do
    get :edit, id: @physiotherapist
    assert_response :success
  end

  test "should update physiotherapist" do
    put :update, id: @physiotherapist, physiotherapist: { login: @physiotherapist.login, nome: @physiotherapist.nome, senha: @physiotherapist.senha }
    assert_redirected_to physiotherapist_path(assigns(:physiotherapist))
  end

  test "should destroy physiotherapist" do
    assert_difference('Physiotherapist.count', -1) do
      delete :destroy, id: @physiotherapist
    end

    assert_redirected_to physiotherapists_path
  end
end
